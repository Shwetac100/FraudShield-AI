package com.fraudshield.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class AiAnalysisResult {
    private String productName;
    private String ingredientsText;
    private String nutritionalInfo;
    @Builder.Default
    private List<String> detectedENumbers = new ArrayList<>();
    @Builder.Default
    private List<String> detectedHarmfulAdditives = new ArrayList<>();
    private String rawAiResponse;
    private boolean isMocked;

    public AiAnalysisResult() {
    }

    public AiAnalysisResult(String productName, String ingredientsText, String nutritionalInfo, List<String> detectedENumbers, List<String> detectedHarmfulAdditives, String rawAiResponse, boolean isMocked) {
        this.productName = productName;
        this.ingredientsText = ingredientsText;
        this.nutritionalInfo = nutritionalInfo;
        this.detectedENumbers = detectedENumbers != null ? detectedENumbers : new ArrayList<>();
        this.detectedHarmfulAdditives = detectedHarmfulAdditives != null ? detectedHarmfulAdditives : new ArrayList<>();
        this.rawAiResponse = rawAiResponse;
        this.isMocked = isMocked;
    }

    public static AiAnalysisResultBuilder builder() {
        return new AiAnalysisResultBuilder();
    }

    public static class AiAnalysisResultBuilder {
        private String productName;
        private String ingredientsText;
        private String nutritionalInfo;
        private List<String> detectedENumbers = new ArrayList<>();
        private List<String> detectedHarmfulAdditives = new ArrayList<>();
        private String rawAiResponse;
        private boolean isMocked;

        AiAnalysisResultBuilder() {
        }

        public AiAnalysisResultBuilder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public AiAnalysisResultBuilder ingredientsText(String ingredientsText) {
            this.ingredientsText = ingredientsText;
            return this;
        }

        public AiAnalysisResultBuilder nutritionalInfo(String nutritionalInfo) {
            this.nutritionalInfo = nutritionalInfo;
            return this;
        }

        public AiAnalysisResultBuilder detectedENumbers(List<String> detectedENumbers) {
            this.detectedENumbers = detectedENumbers;
            return this;
        }

        public AiAnalysisResultBuilder detectedHarmfulAdditives(List<String> detectedHarmfulAdditives) {
            this.detectedHarmfulAdditives = detectedHarmfulAdditives;
            return this;
        }

        public AiAnalysisResultBuilder rawAiResponse(String rawAiResponse) {
            this.rawAiResponse = rawAiResponse;
            return this;
        }

        public AiAnalysisResultBuilder isMocked(boolean isMocked) {
            this.isMocked = isMocked;
            return this;
        }

        public AiAnalysisResult build() {
            return new AiAnalysisResult(productName, ingredientsText, nutritionalInfo, detectedENumbers, detectedHarmfulAdditives, rawAiResponse, isMocked);
        }
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public List<String> getDetectedENumbers() {
        return detectedENumbers;
    }

    public void setDetectedENumbers(List<String> detectedENumbers) {
        this.detectedENumbers = detectedENumbers;
    }

    public List<String> getDetectedHarmfulAdditives() {
        return detectedHarmfulAdditives;
    }

    public void setDetectedHarmfulAdditives(List<String> detectedHarmfulAdditives) {
        this.detectedHarmfulAdditives = detectedHarmfulAdditives;
    }

    public String getRawAiResponse() {
        return rawAiResponse;
    }

    public void setRawAiResponse(String rawAiResponse) {
        this.rawAiResponse = rawAiResponse;
    }

    public boolean isMocked() {
        return isMocked;
    }

    public void setMocked(boolean mocked) {
        isMocked = mocked;
    }
}
