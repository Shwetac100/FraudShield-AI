package com.fraudshield.scoring;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class RiskEvaluationResult {
    private RiskLevel riskLevel;
    private int score; // 0 (safe) to 100 (highly hazardous)
    private String explanation;
    @Builder.Default
    private List<String> flaggedAdditivesOrAdulterants = new ArrayList<>();

    public RiskEvaluationResult() {
    }

    public RiskEvaluationResult(RiskLevel riskLevel, int score, String explanation, List<String> flaggedAdditivesOrAdulterants) {
        this.riskLevel = riskLevel;
        this.score = score;
        this.explanation = explanation;
        this.flaggedAdditivesOrAdulterants = flaggedAdditivesOrAdulterants != null ? flaggedAdditivesOrAdulterants : new ArrayList<>();
    }

    public static RiskEvaluationResultBuilder builder() {
        return new RiskEvaluationResultBuilder();
    }

    public static class RiskEvaluationResultBuilder {
        private RiskLevel riskLevel;
        private int score;
        private String explanation;
        private List<String> flaggedAdditivesOrAdulterants = new ArrayList<>();

        RiskEvaluationResultBuilder() {
        }

        public RiskEvaluationResultBuilder riskLevel(RiskLevel riskLevel) {
            this.riskLevel = riskLevel;
            return this;
        }

        public RiskEvaluationResultBuilder score(int score) {
            this.score = score;
            return this;
        }

        public RiskEvaluationResultBuilder explanation(String explanation) {
            this.explanation = explanation;
            return this;
        }

        public RiskEvaluationResultBuilder flaggedAdditivesOrAdulterants(List<String> flaggedAdditivesOrAdulterants) {
            this.flaggedAdditivesOrAdulterants = flaggedAdditivesOrAdulterants;
            return this;
        }

        public RiskEvaluationResult build() {
            return new RiskEvaluationResult(riskLevel, score, explanation, flaggedAdditivesOrAdulterants);
        }
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public List<String> getFlaggedAdditivesOrAdulterants() {
        return flaggedAdditivesOrAdulterants;
    }

    public void setFlaggedAdditivesOrAdulterants(List<String> flaggedAdditivesOrAdulterants) {
        this.flaggedAdditivesOrAdulterants = flaggedAdditivesOrAdulterants;
    }
}
