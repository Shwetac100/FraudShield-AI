package com.fraudshield.scoring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoringServiceTest {

    private ScoringService scoringService;

    @BeforeEach
    void setUp() {
        scoringService = new ScoringService();
    }

    @Test
    void evaluatePackagedScan_SafeProduct_ReturnsLowRisk() {
        String ingredients = "Water, Sugar, Whole Milk Powder, Natural Vanilla Flavor";
        RiskEvaluationResult result = scoringService.evaluatePackagedScan(ingredients, List.of());

        assertEquals(RiskLevel.LOW, result.getRiskLevel());
        assertTrue(result.getScore() < 15);
    }

    @Test
    void evaluatePackagedScan_ContainsHarmfulAdditives_ReturnsHighRisk() {
        String ingredients = "Water, Sugar, Artificial Flavor, E102, E123, Metanil Yellow";
        RiskEvaluationResult result = scoringService.evaluatePackagedScan(ingredients, List.of("E102", "E123"));

        assertEquals(RiskLevel.HIGH, result.getRiskLevel());
        assertTrue(result.getScore() >= 40);
        assertTrue(result.getFlaggedAdditivesOrAdulterants().contains("E102"));
    }

    @Test
    void evaluateAdulterationTest_PositiveReaction_ReturnsHighRisk() {
        RiskEvaluationResult result = scoringService.evaluateAdulterationTest("Dairy", "Water Trail Test", "Milk flowed immediately leaving no white trail", true);

        assertEquals(RiskLevel.HIGH, result.getRiskLevel());
        assertEquals(75, result.getScore());
    }

    @Test
    void evaluateAdulterationTest_NegativeReaction_ReturnsLowRisk() {
        RiskEvaluationResult result = scoringService.evaluateAdulterationTest("Spices", "Water Test", "No color change observed", false);

        assertEquals(RiskLevel.LOW, result.getRiskLevel());
        assertEquals(5, result.getScore());
    }
}
