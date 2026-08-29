package com.fraudshield.user;

import com.fraudshield.exception.ResourceNotFoundException;
import com.fraudshield.exception.UnauthorizedException;
import com.fraudshield.scan.Scan;
import com.fraudshield.scan.ScanRepository;
import com.fraudshield.scan.ScanService;
import com.fraudshield.scan.dto.ScanResponse;
import com.fraudshield.scoring.RiskLevel;
import com.fraudshield.user.dto.VendorDashboardSummary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorDashboardService {

    private final UserRepository userRepository;
    private final VendorProfileRepository vendorProfileRepository;
    private final ScanRepository scanRepository;
    private final ScanService scanService;

    public VendorDashboardService(UserRepository userRepository, VendorProfileRepository vendorProfileRepository, ScanRepository scanRepository, ScanService scanService) {
        this.userRepository = userRepository;
        this.vendorProfileRepository = vendorProfileRepository;
        this.scanRepository = scanRepository;
        this.scanService = scanService;
    }

    public VendorDashboardSummary getVendorDashboardSummary(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userEmail));

        if (user.getRole() != Role.VENDOR) {
            throw new UnauthorizedException("User is not registered as a vendor");
        }

        VendorProfile profile = vendorProfileRepository.findByUserId(user.getId())
                .orElseGet(() -> VendorProfile.builder()
                        .user(user)
                        .businessName(user.getFullName() + "'s Vendor Profile")
                        .build());

        long total = scanRepository.countByUserId(user.getId());
        long passed = scanRepository.countByUserIdAndRiskLevel(user.getId(), RiskLevel.LOW);
        long flagged = total - passed;
        double compliancePercentage = total > 0 ? Math.round(((double) passed / total) * 100.0 * 10.0) / 10.0 : 100.0;

        List<ScanResponse> recentScans = scanService.getUserScanHistory(userEmail);

        return VendorDashboardSummary.builder()
                .businessName(profile.getBusinessName())
                .businessAddress(profile.getBusinessAddress())
                .businessLicenseNumber(profile.getBusinessLicenseNumber())
                .qualityRating(profile.getQualityRating())
                .totalScans((int) total)
                .passedScans((int) passed)
                .flaggedScans((int) flagged)
                .compliancePercentage(compliancePercentage)
                .recentScans(recentScans)
                .build();
    }
}
