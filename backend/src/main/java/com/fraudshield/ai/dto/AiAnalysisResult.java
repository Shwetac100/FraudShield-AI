package com.fraudshield.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
