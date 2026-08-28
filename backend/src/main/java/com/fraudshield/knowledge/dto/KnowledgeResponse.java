package com.fraudshield.knowledge.dto;

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
public class KnowledgeResponse {
    private Long id;
    private String name;
    private String foodCategory;
    private String description;
    private String commonAdulterants;
    private String homeTestMethod;
    private String healthImpacts;
    private RiskLevel defaultSeverity;
    private String regulatoryLimits;
    private LocalDateTime createdAt;
}
