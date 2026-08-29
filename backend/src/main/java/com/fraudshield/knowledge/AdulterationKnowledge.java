package com.fraudshield.knowledge;

import com.fraudshield.scoring.RiskLevel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "adulteration_knowledge")
public class AdulterationKnowledge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String foodCategory;

    @Column(length = 2000)
    private String description;

    @Column(length = 2000)
    private String commonAdulterants;

    @Column(length = 2000)
    private String homeTestMethod;

    @Column(length = 2000)
    private String healthImpacts;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RiskLevel defaultSeverity;

    @Column
    private String regulatoryLimits;

    @Builder.Default
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public AdulterationKnowledge() {
    }

    public AdulterationKnowledge(Long id, String name, String foodCategory, String description, String commonAdulterants, String homeTestMethod, String healthImpacts, RiskLevel defaultSeverity, String regulatoryLimits, LocalDateTime createdAt) {
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

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public static AdulterationKnowledgeBuilder builder() {
        return new AdulterationKnowledgeBuilder();
    }

    public static class AdulterationKnowledgeBuilder {
        private Long id;
        private String name;
        private String foodCategory;
        private String description;
        private String commonAdulterants;
        private String homeTestMethod;
        private String healthImpacts;
        private RiskLevel defaultSeverity;
        private String regulatoryLimits;
        private LocalDateTime createdAt = LocalDateTime.now();

        AdulterationKnowledgeBuilder() {
        }

        public AdulterationKnowledgeBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AdulterationKnowledgeBuilder name(String name) {
            this.name = name;
            return this;
        }

        public AdulterationKnowledgeBuilder foodCategory(String foodCategory) {
            this.foodCategory = foodCategory;
            return this;
        }

        public AdulterationKnowledgeBuilder description(String description) {
            this.description = description;
            return this;
        }

        public AdulterationKnowledgeBuilder commonAdulterants(String commonAdulterants) {
            this.commonAdulterants = commonAdulterants;
            return this;
        }

        public AdulterationKnowledgeBuilder homeTestMethod(String homeTestMethod) {
            this.homeTestMethod = homeTestMethod;
            return this;
        }

        public AdulterationKnowledgeBuilder healthImpacts(String healthImpacts) {
            this.healthImpacts = healthImpacts;
            return this;
        }

        public AdulterationKnowledgeBuilder defaultSeverity(RiskLevel defaultSeverity) {
            this.defaultSeverity = defaultSeverity;
            return this;
        }

        public AdulterationKnowledgeBuilder regulatoryLimits(String regulatoryLimits) {
            this.regulatoryLimits = regulatoryLimits;
            return this;
        }

        public AdulterationKnowledgeBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public AdulterationKnowledge build() {
            return new AdulterationKnowledge(id, name, foodCategory, description, commonAdulterants, homeTestMethod, healthImpacts, defaultSeverity, regulatoryLimits, createdAt);
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
