package com.fraudshield.scan.dto;

import com.fraudshield.scan.ScanType;
import com.fraudshield.scoring.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScanResponse {

    private Long id;
    private Long userId;
    private ScanType scanType;
    private String productName;
    private RiskLevel riskLevel;
    private String riskExplanation;
    private String summaryResult;
    private String imageUrl;
    private LocalDateTime createdAt;

    private PackagedScanDetailsDto packagedDetails;
    private AdulterationScanDetailsDto adulterationDetails;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PackagedScanDetailsDto {
        private String rawText;
        private String ingredientsText;
        private String nutritionalInfo;
        private String detectedENumbers;
        private String detectedHarmfulAdditives;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AdulterationScanDetailsDto {
        private String foodCategory;
        private String testType;
        private String userObservations;
        private String suspectedAdulterant;
        private Boolean testPositive;
    }
}
