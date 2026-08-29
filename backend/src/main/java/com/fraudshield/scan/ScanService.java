package com.fraudshield.scan;

import com.fraudshield.ai.AiOcrService;
import com.fraudshield.ai.dto.AiAnalysisResult;
import com.fraudshield.exception.BadRequestException;
import com.fraudshield.exception.ResourceNotFoundException;
import com.fraudshield.exception.UnauthorizedException;
import com.fraudshield.scan.dto.CreateScanRequest;
import com.fraudshield.scan.dto.ScanResponse;
import com.fraudshield.scoring.RiskEvaluationResult;
import com.fraudshield.scoring.ScoringService;
import com.fraudshield.user.Role;
import com.fraudshield.user.User;
import com.fraudshield.user.UserRepository;
import com.fraudshield.user.VendorProfile;
import com.fraudshield.user.VendorProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScanService {

    private final ScanRepository scanRepository;
    private final UserRepository userRepository;
    private final VendorProfileRepository vendorProfileRepository;
    private final AiOcrService aiOcrService;
    private final ScoringService scoringService;

    public ScanService(ScanRepository scanRepository, UserRepository userRepository, VendorProfileRepository vendorProfileRepository, AiOcrService aiOcrService, ScoringService scoringService) {
        this.scanRepository = scanRepository;
        this.userRepository = userRepository;
        this.vendorProfileRepository = vendorProfileRepository;
        this.aiOcrService = aiOcrService;
        this.scoringService = scoringService;
    }

    @Transactional
    public ScanResponse createScan(String userEmail, CreateScanRequest request) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userEmail));

        Scan scan = Scan.builder()
                .user(user)
                .scanType(request.getScanType())
                .imageUrl(request.getImageUrl())
                .build();

        if (request.getScanType() == ScanType.PACKAGED) {
            processPackagedScan(scan, request);
        } else if (request.getScanType() == ScanType.ADULTERATION) {
            processAdulterationScan(scan, request);
        } else {
            throw new BadRequestException("Invalid scan type");
        }

        Scan savedScan = scanRepository.save(scan);

        // Update vendor statistics if user is a VENDOR
        if (user.getRole() == Role.VENDOR) {
            updateVendorStats(user.getId());
        }

        return mapToResponse(savedScan);
    }

    private void processPackagedScan(Scan scan, CreateScanRequest request) {
        AiAnalysisResult aiResult = aiOcrService.analyzeFoodLabel(request.getImageUrl(), request.getRawIngredientsText());

        String productName = request.getProductName() != null && !request.getProductName().isBlank()
                ? request.getProductName()
                : aiResult.getProductName();

        RiskEvaluationResult riskResult = scoringService.evaluatePackagedScan(
                aiResult.getIngredientsText(),
                aiResult.getDetectedENumbers()
        );

        scan.setProductName(productName);
        scan.setRiskLevel(riskResult.getRiskLevel());
        scan.setRiskExplanation(riskResult.getExplanation());
        scan.setSummaryResult("Risk Level: " + riskResult.getRiskLevel() + " (Score: " + riskResult.getScore() + "/100)");

        PackagedScanDetails details = PackagedScanDetails.builder()
                .scan(scan)
                .rawText(request.getRawIngredientsText())
                .ingredientsText(aiResult.getIngredientsText())
                .nutritionalInfo(aiResult.getNutritionalInfo())
                .detectedENumbers(String.join(", ", aiResult.getDetectedENumbers()))
                .detectedHarmfulAdditives(String.join(", ", aiResult.getDetectedHarmfulAdditives()))
                .build();

        scan.setPackagedScanDetails(details);
    }

    private void processAdulterationScan(Scan scan, CreateScanRequest request) {
        if (request.getFoodCategory() == null || request.getFoodCategory().isBlank()) {
            throw new BadRequestException("Food category is required for home adulteration test");
        }

        RiskEvaluationResult riskResult = scoringService.evaluateAdulterationTest(
                request.getFoodCategory(),
                request.getTestType(),
                request.getUserObservations(),
                request.getTestPositive()
        );

        String productName = request.getProductName() != null && !request.getProductName().isBlank()
                ? request.getProductName()
                : request.getFoodCategory() + " Adulteration Screening";

        scan.setProductName(productName);
        scan.setRiskLevel(riskResult.getRiskLevel());
        scan.setRiskExplanation(riskResult.getExplanation());
        scan.setSummaryResult("Adulteration Test Result: " + (Boolean.TRUE.equals(request.getTestPositive()) ? "POSITIVE" : "NEGATIVE"));

        AdulterationScanDetails details = AdulterationScanDetails.builder()
                .scan(scan)
                .foodCategory(request.getFoodCategory())
                .testType(request.getTestType() != null ? request.getTestType() : "Standard Visual/Chemical Test")
                .userObservations(request.getUserObservations() != null ? request.getUserObservations() : "User completed guided test")
                .suspectedAdulterant(riskResult.getFlaggedAdditivesOrAdulterants().isEmpty() ? "None" : String.join(", ", riskResult.getFlaggedAdditivesOrAdulterants()))
                .testPositive(request.getTestPositive())
                .build();

        scan.setAdulterationScanDetails(details);
    }

    public ScanResponse getScanById(Long scanId, String userEmail) {
        Scan scan = scanRepository.findById(scanId)
                .orElseThrow(() -> new ResourceNotFoundException("Scan record not found with ID: " + scanId));

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userEmail));

        // Ownership check unless ADMIN
        if (!scan.getUser().getId().equals(user.getId()) && user.getRole() != Role.ADMIN) {
            throw new UnauthorizedException("Access denied for scan ID: " + scanId);
        }

        return mapToResponse(scan);
    }

    public List<ScanResponse> getUserScanHistory(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userEmail));

        List<Scan> scans = scanRepository.findByUserIdOrderByCreatedAtDesc(user.getId());
        return scans.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private void updateVendorStats(Long userId) {
        Optional<VendorProfile> profileOpt = vendorProfileRepository.findByUserId(userId);
        if (profileOpt.isPresent()) {
            VendorProfile profile = profileOpt.get();
            long total = scanRepository.countByUserId(userId);
            long lowRisk = scanRepository.countByUserIdAndRiskLevel(userId, com.fraudshield.scoring.RiskLevel.LOW);

            profile.setTotalScans((int) total);
            profile.setPassedScans((int) lowRisk);
            double rating = total > 0 ? Math.round(((double) lowRisk / total) * 5.0 * 10.0) / 10.0 : 5.0;
            profile.setQualityRating(rating);
            vendorProfileRepository.save(profile);
        }
    }

    private ScanResponse mapToResponse(Scan scan) {
        ScanResponse.PackagedScanDetailsDto packagedDto = null;
        if (scan.getPackagedScanDetails() != null) {
            PackagedScanDetails p = scan.getPackagedScanDetails();
            packagedDto = ScanResponse.PackagedScanDetailsDto.builder()
                    .rawText(p.getRawText())
                    .ingredientsText(p.getIngredientsText())
                    .nutritionalInfo(p.getNutritionalInfo())
                    .detectedENumbers(p.getDetectedENumbers())
                    .detectedHarmfulAdditives(p.getDetectedHarmfulAdditives())
                    .build();
        }

        ScanResponse.AdulterationScanDetailsDto adulterationDto = null;
        if (scan.getAdulterationScanDetails() != null) {
            AdulterationScanDetails a = scan.getAdulterationScanDetails();
            adulterationDto = ScanResponse.AdulterationScanDetailsDto.builder()
                    .foodCategory(a.getFoodCategory())
                    .testType(a.getTestType())
                    .userObservations(a.getUserObservations())
                    .suspectedAdulterant(a.getSuspectedAdulterant())
                    .testPositive(a.getTestPositive())
                    .build();
        }

        return ScanResponse.builder()
                .id(scan.getId())
                .userId(scan.getUser().getId())
                .scanType(scan.getScanType())
                .productName(scan.getProductName())
                .riskLevel(scan.getRiskLevel())
                .riskExplanation(scan.getRiskExplanation())
                .summaryResult(scan.getSummaryResult())
                .imageUrl(scan.getImageUrl())
                .createdAt(scan.getCreatedAt())
                .packagedDetails(packagedDto)
                .adulterationDetails(adulterationDto)
                .build();
    }
}
