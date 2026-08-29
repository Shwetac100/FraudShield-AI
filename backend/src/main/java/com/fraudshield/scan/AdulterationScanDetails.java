package com.fraudshield.scan;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adulteration_scan_details")
public class AdulterationScanDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "scan_id", nullable = false, unique = true)
    private Scan scan;

    @Column(nullable = false)
    private String foodCategory;

    @Column(nullable = false)
    private String testType;

    @Column(length = 2000, nullable = false)
    private String userObservations;

    @Column
    private String suspectedAdulterant;

    @Column
    private Boolean testPositive;

    public AdulterationScanDetails() {
    }

    public AdulterationScanDetails(Long id, Scan scan, String foodCategory, String testType, String userObservations, String suspectedAdulterant, Boolean testPositive) {
        this.id = id;
        this.scan = scan;
        this.foodCategory = foodCategory;
        this.testType = testType;
        this.userObservations = userObservations;
        this.suspectedAdulterant = suspectedAdulterant;
        this.testPositive = testPositive;
    }

    public static AdulterationScanDetailsBuilder builder() {
        return new AdulterationScanDetailsBuilder();
    }

    public static class AdulterationScanDetailsBuilder {
        private Long id;
        private Scan scan;
        private String foodCategory;
        private String testType;
        private String userObservations;
        private String suspectedAdulterant;
        private Boolean testPositive;

        AdulterationScanDetailsBuilder() {
        }

        public AdulterationScanDetailsBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AdulterationScanDetailsBuilder scan(Scan scan) {
            this.scan = scan;
            return this;
        }

        public AdulterationScanDetailsBuilder foodCategory(String foodCategory) {
            this.foodCategory = foodCategory;
            return this;
        }

        public AdulterationScanDetailsBuilder testType(String testType) {
            this.testType = testType;
            return this;
        }

        public AdulterationScanDetailsBuilder userObservations(String userObservations) {
            this.userObservations = userObservations;
            return this;
        }

        public AdulterationScanDetailsBuilder suspectedAdulterant(String suspectedAdulterant) {
            this.suspectedAdulterant = suspectedAdulterant;
            return this;
        }

        public AdulterationScanDetailsBuilder testPositive(Boolean testPositive) {
            this.testPositive = testPositive;
            return this;
        }

        public AdulterationScanDetails build() {
            return new AdulterationScanDetails(id, scan, foodCategory, testType, userObservations, suspectedAdulterant, testPositive);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Scan getScan() {
        return scan;
    }

    public void setScan(Scan scan) {
        this.scan = scan;
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

    public String getSuspectedAdulterant() {
        return suspectedAdulterant;
    }

    public void setSuspectedAdulterant(String suspectedAdulterant) {
        this.suspectedAdulterant = suspectedAdulterant;
    }

    public Boolean getTestPositive() {
        return testPositive;
    }

    public void setTestPositive(Boolean testPositive) {
        this.testPositive = testPositive;
    }
}
