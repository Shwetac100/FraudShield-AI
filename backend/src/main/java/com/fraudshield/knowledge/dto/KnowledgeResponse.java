package com.fraudshield.knowledge.dto;

import com.fraudshield.scoring.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    public KnowledgeResponse() {
    }

    public KnowledgeResponse(Long id, String name, String foodCategory, String description, String commonAdulterants, String homeTestMethod, String healthImpacts, RiskLevel defaultSeverity, String regulatoryLimits, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.foodCategory = foodCategory;
        this.description = description;
        this.commonAdulterants = commonAdulterants;
        this.homeTestMethod = homeTestMethod;
        this.healthImpacts = healthImpacts;
        this.defaultSeverity = defaultSeverity;
        this.regulatoryLimits = regulatoryLimits;
        this.createdAt = createdAt;
    }

    public static KnowledgeResponseBuilder builder() {
        return new KnowledgeResponseBuilder();
    }

    public static class KnowledgeResponseBuilder {
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

        KnowledgeResponseBuilder() {
        }

        public KnowledgeResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public KnowledgeResponseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public KnowledgeResponseBuilder foodCategory(String foodCategory) {
            this.foodCategory = foodCategory;
            return this;
        }

        public KnowledgeResponseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public KnowledgeResponseBuilder commonAdulterants(String commonAdulterants) {
            this.commonAdulterants = commonAdulterants;
            return this;
        }

        public KnowledgeResponseBuilder homeTestMethod(String homeTestMethod) {
            this.homeTestMethod = homeTestMethod;
            return this;
        }

        public KnowledgeResponseBuilder healthImpacts(String healthImpacts) {
            this.healthImpacts = healthImpacts;
            return this;
        }

        public KnowledgeResponseBuilder defaultSeverity(RiskLevel defaultSeverity) {
            this.defaultSeverity = defaultSeverity;
            return this;
        }

        public KnowledgeResponseBuilder regulatoryLimits(String regulatoryLimits) {
            this.regulatoryLimits = regulatoryLimits;
            return this;
        }

        public KnowledgeResponseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public KnowledgeResponse build() {
            return new KnowledgeResponse(id, name, foodCategory, description, commonAdulterants, homeTestMethod, healthImpacts, defaultSeverity, regulatoryLimits, createdAt);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCommonAdulterants() {
        return commonAdulterants;
    }

    public void setCommonAdulterants(String commonAdulterants) {
        this.commonAdulterants = commonAdulterants;
    }

    public String getHomeTestMethod() {
        return homeTestMethod;
    }

    public void setHomeTestMethod(String homeTestMethod) {
        this.homeTestMethod = homeTestMethod;
    }

    public String getHealthImpacts() {
        return healthImpacts;
    }

    public void setHealthImpacts(String healthImpacts) {
        this.healthImpacts = healthImpacts;
    }

    public RiskLevel getDefaultSeverity() {
        return defaultSeverity;
    }

    public void setDefaultSeverity(RiskLevel defaultSeverity) {
        this.defaultSeverity = defaultSeverity;
    }

    public String getRegulatoryLimits() {
        return regulatoryLimits;
    }

    public void setRegulatoryLimits(String regulatoryLimits) {
        this.regulatoryLimits = regulatoryLimits;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
