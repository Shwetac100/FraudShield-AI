package com.fraudshield.scan.dto;

import com.fraudshield.scan.ScanType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Schema(description = "Request object for creating a new scan")
public class CreateScanRequest {

    @NotNull(message = "Scan type is required")
    @Schema(description = "Type of scan (PACKAGED or ADULTERATION)", example = "PACKAGED")
    private ScanType scanType;

    @Schema(description = "Name of product being scanned", example = "Organic Whole Milk")
    private String productName;

    @Schema(description = "URL of scanned product or ingredient label image")
    private String imageUrl;

    // For PACKAGED scan type
    @Schema(description = "Raw ingredients text extracted or entered for PACKAGED scan", example = "Milk, Vitamin D3, E211")
    private String rawIngredientsText;

    // For ADULTERATION scan type
    @Schema(description = "Food category for ADULTERATION scan", example = "Dairy")
    private String foodCategory;

    @Schema(description = "Testing method used for adulteration detection", example = "Water Dilution Test")
    private String testType;

    @Schema(description = "User observations during home test", example = "Milk separated immediately with blue tincture drop")
    private String userObservations;

    @Schema(description = "Whether adulteration test produced positive result", example = "true")
    private Boolean testPositive;

    public CreateScanRequest() {
    }

    public CreateScanRequest(ScanType scanType, String productName, String imageUrl, String rawIngredientsText, String foodCategory, String testType, String userObservations, Boolean testPositive) {
        this.scanType = scanType;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.rawIngredientsText = rawIngredientsText;
        this.foodCategory = foodCategory;
        this.testType = testType;
        this.userObservations = userObservations;
        this.testPositive = testPositive;
    }

    public static CreateScanRequestBuilder builder() {
        return new CreateScanRequestBuilder();
    }

    public static class CreateScanRequestBuilder {
        private ScanType scanType;
        private String productName;
        private String imageUrl;
        private String rawIngredientsText;
        private String foodCategory;
        private String testType;
        private String userObservations;
        private Boolean testPositive;

        CreateScanRequestBuilder() {
        }

        public CreateScanRequestBuilder scanType(ScanType scanType) {
            this.scanType = scanType;
            return this;
        }

        public CreateScanRequestBuilder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public CreateScanRequestBuilder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public CreateScanRequestBuilder rawIngredientsText(String rawIngredientsText) {
            this.rawIngredientsText = rawIngredientsText;
            return this;
        }

        public CreateScanRequestBuilder foodCategory(String foodCategory) {
            this.foodCategory = foodCategory;
            return this;
        }

        public CreateScanRequestBuilder testType(String testType) {
            this.testType = testType;
            return this;
        }

        public CreateScanRequestBuilder userObservations(String userObservations) {
            this.userObservations = userObservations;
            return this;
        }

        public CreateScanRequestBuilder testPositive(Boolean testPositive) {
            this.testPositive = testPositive;
            return this;
        }

        public CreateScanRequest build() {
            return new CreateScanRequest(scanType, productName, imageUrl, rawIngredientsText, foodCategory, testType, userObservations, testPositive);
        }
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRawIngredientsText() {
        return rawIngredientsText;
    }

    public void setRawIngredientsText(String rawIngredientsText) {
        this.rawIngredientsText = rawIngredientsText;
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

    public Boolean getTestPositive() {
        return testPositive;
    }

    public void setTestPositive(Boolean testPositive) {
        this.testPositive = testPositive;
    }
}
