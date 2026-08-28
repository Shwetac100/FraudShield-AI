package com.fraudshield.scoring;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ScoringService {

    // Known dangerous or restricted E-numbers and chemical additives
    private static final Map<String, Integer> HIGH_RISK_ADDITIVES = Map.of(
            "E102", 30, // Tartrazine
            "E110", 30, // Sunset Yellow
            "E122", 35, // Azorubine
            "E123", 45, // Amaranth
            "E124", 35, // Ponceau 4R
            "E127", 40, // Erythrosine
            "E129", 35, // Allura Red
            "E211", 25, // Sodium Benzoate
            "E220", 30, // Sulfur Dioxide
            "E250", 40, // Sodium Nitrite
            "E251", 35, // Sodium Nitrate
            "E320", 30, // BHA
            "E321", 30, // BHT
            "E951", 25, // Aspartame
            "E952", 30  // Cyclamate
    );

    private static final Map<String, Integer> ADULTERANT_SEVERITY_SCORES = Map.of(
            "METANIL YELLOW", 50,
            "LEAD CHROMATE", 60,
            "RHODAMINE B", 55,
            "BRICK POWDER", 45,
            "DETERGENT", 50,
            "UREA", 45,
            "STARCH", 25,
            "WATER", 15,
            "SUGAR SYRUP", 20,
            "CHALK POWDER", 30
    );

    public RiskEvaluationResult evaluatePackagedScan(String ingredients, List<String> extractedENumbers) {
        int totalScore = 0;
        List<String> flaggedList = new ArrayList<>();
        StringBuilder explanation = new StringBuilder();

        String combinedText = ((ingredients != null ? ingredients : "") + " " +
                (extractedENumbers != null ? String.join(" ", extractedENumbers) : "")).toUpperCase();

        // 1. Check for E-numbers
        for (Map.Entry<String, Integer> entry : HIGH_RISK_ADDITIVES.entrySet()) {
            if (combinedText.contains(entry.getKey())) {
                totalScore += entry.getValue();
                flaggedList.add(entry.getKey());
            }
        }

        // 2. Check for explicit keywords or adulterants
        for (Map.Entry<String, Integer> entry : ADULTERANT_SEVERITY_SCORES.entrySet()) {
            if (combinedText.contains(entry.getKey())) {
                totalScore += entry.getValue();
                flaggedList.add(entry.getKey());
            }
        }

        // 3. Excessive sugar or palm oil check
        if (combinedText.contains("PALM OIL") || combinedText.contains("HYDROGENATED OIL")) {
            totalScore += 15;
            flaggedList.add("Unhealthy Fats (Palm/Hydrogenated)");
        }
        if (combinedText.contains("HIGH FRUCTOSE CORN SYRUP")) {
            totalScore += 20;
            flaggedList.add("High Fructose Corn Syrup");
        }

        RiskLevel riskLevel;
        if (totalScore >= 40) {
            riskLevel = RiskLevel.HIGH;
            explanation.append("High adulteration/health risk detected. ");
        } else if (totalScore >= 15) {
            riskLevel = RiskLevel.MEDIUM;
            explanation.append("Moderate health risk or artificial additives detected. ");
        } else {
            riskLevel = RiskLevel.LOW;
            explanation.append("Low risk detected. No major harmful additives or adulterants identified. ");
        }

        if (!flaggedList.isEmpty()) {
            explanation.append("Flagged items: ").append(String.join(", ", flaggedList)).append(".");
        } else {
            explanation.append("Ingredients appear within standard safety limits.");
        }

        return RiskEvaluationResult.builder()
                .riskLevel(riskLevel)
                .score(Math.min(totalScore, 100))
                .explanation(explanation.toString())
                .flaggedAdditivesOrAdulterants(flaggedList)
                .build();
    }

    public RiskEvaluationResult evaluateAdulterationTest(String category, String testType, String observations, Boolean testPositive) {
        int score = 0;
        List<String> flagged = new ArrayList<>();
        StringBuilder explanation = new StringBuilder();

        boolean isPositive = Boolean.TRUE.equals(testPositive) ||
                (observations != null && (
                        observations.toLowerCase().contains("red") ||
                        observations.toLowerCase().contains("trail") ||
                        observations.toLowerCase().contains("settled") ||
                        observations.toLowerCase().contains("precipitate") ||
                        observations.toLowerCase().contains("color change") ||
                        observations.toLowerCase().contains("positive")
                ));

        if (isPositive) {
            score = 75;
            explanation.append("Adulteration test indicated POSITIVE reaction. ");
            flagged.add("Suspected Adulterant in " + category);
        } else {
            score = 5;
            explanation.append("Adulteration test indicated NEGATIVE reaction. Sample appears normal. ");
        }

        RiskLevel riskLevel = score >= 50 ? RiskLevel.HIGH : (score >= 20 ? RiskLevel.MEDIUM : RiskLevel.LOW);

        return RiskEvaluationResult.builder()
                .riskLevel(riskLevel)
                .score(score)
                .explanation(explanation.toString())
                .flaggedAdditivesOrAdulterants(flagged)
                .build();
    }
}
