package com.fraudshield.scan.dto;

import com.fraudshield.scan.ScanType;
import com.fraudshield.scoring.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    public ScanResponse() {
    }

    public ScanResponse(Long id, Long userId, ScanType scanType, String productName, RiskLevel riskLevel, String riskExplanation, String summaryResult, String imageUrl, LocalDateTime createdAt, PackagedScanDetailsDto packagedDetails, AdulterationScanDetailsDto adulterationDetails) {
        this.id = id;
        this.userId = userId;
        this.scanType = scanType;
        this.productName = productName;
        this.riskLevel = riskLevel;
        this.riskExplanation = riskExplanation;
        this.summaryResult = summaryResult;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.packagedDetails = packagedDetails;
        this.adulterationDetails = adulterationDetails;
    }

    public static ScanResponseBuilder builder() {
        return new ScanResponseBuilder();
    }

    public static class ScanResponseBuilder {
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

        ScanResponseBuilder() {
        }

        public ScanResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ScanResponseBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public ScanResponseBuilder scanType(ScanType scanType) {
            this.scanType = scanType;
            return this;
        }

        public ScanResponseBuilder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public ScanResponseBuilder riskLevel(RiskLevel riskLevel) {
            this.riskLevel = riskLevel;
            return this;
        }

        public ScanResponseBuilder riskExplanation(String riskExplanation) {
            this.riskExplanation = riskExplanation;
            return this;
        }

        public ScanResponseBuilder summaryResult(String summaryResult) {
            this.summaryResult = summaryResult;
            return this;
        }

        public ScanResponseBuilder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public ScanResponseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public ScanResponseBuilder packagedDetails(PackagedScanDetailsDto packagedDetails) {
            this.packagedDetails = packagedDetails;
            return this;
        }

        public ScanResponseBuilder adulterationDetails(AdulterationScanDetailsDto adulterationDetails) {
            this.adulterationDetails = adulterationDetails;
            return this;
        }

        public ScanResponse build() {
            return new ScanResponse(id, userId, scanType, productName, riskLevel, riskExplanation, summaryResult, imageUrl, createdAt, packagedDetails, adulterationDetails);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ScanType getScanType() {
        return scanType;
    }

    public void setScanType(ScanType scanType) {
        this.scanType = scanType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getRiskExplanation() {
        return riskExplanation;
    }

    public void setRiskExplanation(String riskExplanation) {
        this.riskExplanation = riskExplanation;
    }

    public String getSummaryResult() {
        return summaryResult;
    }

    public void setSummaryResult(String summaryResult) {
        this.summaryResult = summaryResult;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public PackagedScanDetailsDto getPackagedDetails() {
        return packagedDetails;
    }

    public void setPackagedDetails(PackagedScanDetailsDto packagedDetails) {
        this.packagedDetails = packagedDetails;
    }

    public AdulterationScanDetailsDto getAdulterationDetails() {
        return adulterationDetails;
    }

    public void setAdulterationDetails(AdulterationScanDetailsDto adulterationDetails) {
        this.adulterationDetails = adulterationDetails;
    }

    public static class PackagedScanDetailsDto {
        private String rawText;
        private String ingredientsText;
        private String nutritionalInfo;
        private String detectedENumbers;
        private String detectedHarmfulAdditives;

        public PackagedScanDetailsDto() {
        }

        public PackagedScanDetailsDto(String rawText, String ingredientsText, String nutritionalInfo, String detectedENumbers, String detectedHarmfulAdditives) {
            this.rawText = rawText;
            this.ingredientsText = ingredientsText;
            this.nutritionalInfo = nutritionalInfo;
            this.detectedENumbers = detectedENumbers;
            this.detectedHarmfulAdditives = detectedHarmfulAdditives;
        }

        public static PackagedScanDetailsDtoBuilder builder() {
            return new PackagedScanDetailsDtoBuilder();
        }

        public static class PackagedScanDetailsDtoBuilder {
            private String rawText;
            private String ingredientsText;
            private String nutritionalInfo;
            private String detectedENumbers;
            private String detectedHarmfulAdditives;

            PackagedScanDetailsDtoBuilder() {
            }

            public PackagedScanDetailsDtoBuilder rawText(String rawText) {
                this.rawText = rawText;
                return this;
            }

            public PackagedScanDetailsDtoBuilder ingredientsText(String ingredientsText) {
                this.ingredientsText = ingredientsText;
                return this;
            }

            public PackagedScanDetailsDtoBuilder nutritionalInfo(String nutritionalInfo) {
                this.nutritionalInfo = nutritionalInfo;
                return this;
            }

            public PackagedScanDetailsDtoBuilder detectedENumbers(String detectedENumbers) {
                this.detectedENumbers = detectedENumbers;
                return this;
            }

            public PackagedScanDetailsDtoBuilder detectedHarmfulAdditives(String detectedHarmfulAdditives) {
                this.detectedHarmfulAdditives = detectedHarmfulAdditives;
                return this;
            }

            public PackagedScanDetailsDto build() {
                return new PackagedScanDetailsDto(rawText, ingredientsText, nutritionalInfo, detectedENumbers, detectedHarmfulAdditives);
            }
        }

        public String getRawText() {
            return rawText;
        }

        public void setRawText(String rawText) {
            this.rawText = rawText;
        }

        public String getIngredientsText() {
            return ingredientsText;
        }

        public void setIngredientsText(String ingredientsText) {
            this.ingredientsText = ingredientsText;
        }

        public String getNutritionalInfo() {
            return nutritionalInfo;
        }

        public void setNutritionalInfo(String nutritionalInfo) {
            this.nutritionalInfo = nutritionalInfo;
        }

        public String getDetectedENumbers() {
            return detectedENumbers;
        }

        public void setDetectedENumbers(String detectedENumbers) {
            this.detectedENumbers = detectedENumbers;
        }

        public String getDetectedHarmfulAdditives() {
            return detectedHarmfulAdditives;
        }

        public void setDetectedHarmfulAdditives(String detectedHarmfulAdditives) {
            this.detectedHarmfulAdditives = detectedHarmfulAdditives;
        }
    }

    public static class AdulterationScanDetailsDto {
        private String foodCategory;
        private String testType;
        private String userObservations;
        private String suspectedAdulterant;
        private Boolean testPositive;

        public AdulterationScanDetailsDto() {
        }

        public AdulterationScanDetailsDto(String foodCategory, String testType, String userObservations, String suspectedAdulterant, Boolean testPositive) {
            this.foodCategory = foodCategory;
            this.testType = testType;
            this.userObservations = userObservations;
            this.suspectedAdulterant = suspectedAdulterant;
            this.testPositive = testPositive;
        }

        public static AdulterationScanDetailsDtoBuilder builder() {
            return new AdulterationScanDetailsDtoBuilder();
        }

        public static class AdulterationScanDetailsDtoBuilder {
            private String foodCategory;
            private String testType;
            private String userObservations;
            private String suspectedAdulterant;
            private Boolean testPositive;

            AdulterationScanDetailsDtoBuilder() {
            }

            public AdulterationScanDetailsDtoBuilder foodCategory(String foodCategory) {
                this.foodCategory = foodCategory;
                return this;
            }

            public AdulterationScanDetailsDtoBuilder testType(String testType) {
                this.testType = testType;
                return this;
            }

            public AdulterationScanDetailsDtoBuilder userObservations(String userObservations) {
                this.userObservations = userObservations;
                return this;
            }

            public AdulterationScanDetailsDtoBuilder suspectedAdulterant(String suspectedAdulterant) {
                this.suspectedAdulterant = suspectedAdulterant;
                return this;
            }

            public AdulterationScanDetailsDtoBuilder testPositive(Boolean testPositive) {
                this.testPositive = testPositive;
                return this;
            }

            public AdulterationScanDetailsDto build() {
                return new AdulterationScanDetailsDto(foodCategory, testType, userObservations, suspectedAdulterant, testPositive);
            }
        }

        public String getFoodCategory() {
            return foodCategory;
        }

        public void setFoodCategory(String foodCategory) {
            this.foodCategory = foodCategory;
        }

        public String getTestType() {
            return testType;
        }

        public void setTestType(String testType) {
            this.testType = testType;
        }

        public String getUserObservations() {
            return userObservations;
        }

        public void setUserObservations(String userObservations) {
            this.userObservations = userObservations;
        }

        public String getSuspectedAdulterant() {
            return suspectedAdulterant;
        }

        public void setSuspectedAdulterant(String suspectedAdulterant) {
            this.suspectedAdulterant = suspectedAdulterant;
        }

        public Boolean getTestPositive() {
            return testPositive;
        }

        public void setTestPositive(Boolean testPositive) {
            this.testPositive = testPositive;
        }
    }
}
