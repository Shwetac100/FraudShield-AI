package com.fraudshield.scoring;

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
public class RiskEvaluationResult {
    private RiskLevel riskLevel;
    private int score; // 0 (safe) to 100 (highly hazardous)
    private String explanation;
    @Builder.Default
    private List<String> flaggedAdditivesOrAdulterants = new ArrayList<>();
}
