package com.fraudshield.scan;

import com.fraudshield.ai.AiOcrService;
import com.fraudshield.ai.dto.AiAnalysisResult;
import com.fraudshield.scan.dto.CreateScanRequest;
import com.fraudshield.scan.dto.ScanResponse;
import com.fraudshield.scoring.RiskEvaluationResult;
import com.fraudshield.scoring.RiskLevel;
import com.fraudshield.scoring.ScoringService;
import com.fraudshield.user.Role;
import com.fraudshield.user.User;
import com.fraudshield.user.UserRepository;
import com.fraudshield.user.VendorProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScanServiceTest {

    @Mock
    private ScanRepository scanRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private VendorProfileRepository vendorProfileRepository;

    @Mock
    private AiOcrService aiOcrService;

    @Mock
    private ScoringService scoringService;

    @InjectMocks
    private ScanService scanService;

    private User mockUser;

    @BeforeEach
    void setUp() {
        mockUser = User.builder()
                .id(1L)
                .email("consumer@example.com")
                .fullName("Consumer User")
                .role(Role.CONSUMER)
                .build();
    }

    @Test
    void createPackagedScan_Success() {
        CreateScanRequest request = CreateScanRequest.builder()
                .scanType(ScanType.PACKAGED)
                .productName("Sample Fruit Drink")
                .rawIngredientsText("Water, Sugar, E102")
                .build();

        AiAnalysisResult aiResult = AiAnalysisResult.builder()
                .productName("Sample Fruit Drink")
                .ingredientsText("Water, Sugar, E102")
                .detectedENumbers(List.of("E102"))
                .detectedHarmfulAdditives(List.of("Tartrazine"))
                .isMocked(true)
                .build();

        RiskEvaluationResult riskResult = RiskEvaluationResult.builder()
                .riskLevel(RiskLevel.MEDIUM)
                .score(30)
                .explanation("Contains E102")
                .flaggedAdditivesOrAdulterants(List.of("E102"))
                .build();

        Scan savedScan = Scan.builder()
                .id(10L)
                .user(mockUser)
                .scanType(ScanType.PACKAGED)
                .productName("Sample Fruit Drink")
                .riskLevel(RiskLevel.MEDIUM)
                .riskExplanation("Contains E102")
                .summaryResult("Risk Level: MEDIUM")
                .build();

        when(userRepository.findByEmail("consumer@example.com")).thenReturn(Optional.of(mockUser));
        when(aiOcrService.analyzeFoodLabel(any(), eq("Water, Sugar, E102"))).thenReturn(aiResult);
        when(scoringService.evaluatePackagedScan(eq("Water, Sugar, E102"), eq(List.of("E102")))).thenReturn(riskResult);
        when(scanRepository.save(any(Scan.class))).thenReturn(savedScan);

        ScanResponse response = scanService.createScan("consumer@example.com", request);

        assertNotNull(response);
        assertEquals(ScanType.PACKAGED, response.getScanType());
        assertEquals("Sample Fruit Drink", response.getProductName());
        assertEquals(RiskLevel.MEDIUM, response.getRiskLevel());
    }
}
