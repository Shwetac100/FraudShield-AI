package com.fraudshield.scan;

import com.fraudshield.scoring.RiskLevel;
import com.fraudshield.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "scans")
public class Scan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScanType scanType;

    @Column
    private String productName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RiskLevel riskLevel;

    @Column(length = 2000)
    private String riskExplanation;

    @Column(length = 1000)
    private String summaryResult;

    @Column
    private String imageUrl;

    @OneToOne(mappedBy = "scan", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private PackagedScanDetails packagedScanDetails;

    @OneToOne(mappedBy = "scan", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private AdulterationScanDetails adulterationScanDetails;

    @Builder.Default
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Scan() {
    }

    public Scan(Long id, User user, ScanType scanType, String productName, RiskLevel riskLevel, String riskExplanation, String summaryResult, String imageUrl, PackagedScanDetails packagedScanDetails, AdulterationScanDetails adulterationScanDetails, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.scanType = scanType;
        this.productName = productName;
        this.riskLevel = riskLevel;
        this.riskExplanation = riskExplanation;
        this.summaryResult = summaryResult;
        this.imageUrl = imageUrl;
        this.packagedScanDetails = packagedScanDetails;
        this.adulterationScanDetails = adulterationScanDetails;
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public static ScanBuilder builder() {
        return new ScanBuilder();
    }

    public static class ScanBuilder {
        private Long id;
        private User user;
        private ScanType scanType;
        private String productName;
        private RiskLevel riskLevel;
        private String riskExplanation;
        private String summaryResult;
        private String imageUrl;
        private PackagedScanDetails packagedScanDetails;
        private AdulterationScanDetails adulterationScanDetails;
        private LocalDateTime createdAt = LocalDateTime.now();

        ScanBuilder() {
        }

        public ScanBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ScanBuilder user(User user) {
            this.user = user;
            return this;
        }

        public ScanBuilder scanType(ScanType scanType) {
            this.scanType = scanType;
            return this;
        }

        public ScanBuilder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public ScanBuilder riskLevel(RiskLevel riskLevel) {
            this.riskLevel = riskLevel;
            return this;
        }

        public ScanBuilder riskExplanation(String riskExplanation) {
            this.riskExplanation = riskExplanation;
            return this;
        }

        public ScanBuilder summaryResult(String summaryResult) {
            this.summaryResult = summaryResult;
            return this;
        }

        public ScanBuilder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public ScanBuilder packagedScanDetails(PackagedScanDetails packagedScanDetails) {
            this.packagedScanDetails = packagedScanDetails;
            return this;
        }

        public ScanBuilder adulterationScanDetails(AdulterationScanDetails adulterationScanDetails) {
            this.adulterationScanDetails = adulterationScanDetails;
            return this;
        }

        public ScanBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Scan build() {
            return new Scan(id, user, scanType, productName, riskLevel, riskExplanation, summaryResult, imageUrl, packagedScanDetails, adulterationScanDetails, createdAt);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ScanType getScanType() {
        return scanType;
    }

    public void setScanType(ScanType scanType) {
        this.scanType = scanType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getRiskExplanation() {
        return riskExplanation;
    }

    public void setRiskExplanation(String riskExplanation) {
        this.riskExplanation = riskExplanation;
    }

    public String getSummaryResult() {
        return summaryResult;
    }

    public void setSummaryResult(String summaryResult) {
        this.summaryResult = summaryResult;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public PackagedScanDetails getPackagedScanDetails() {
        return packagedScanDetails;
    }

    public void setPackagedScanDetails(PackagedScanDetails packagedScanDetails) {
        this.packagedScanDetails = packagedScanDetails;
    }

    public AdulterationScanDetails getAdulterationScanDetails() {
        return adulterationScanDetails;
    }

    public void setAdulterationScanDetails(AdulterationScanDetails adulterationScanDetails) {
        this.adulterationScanDetails = adulterationScanDetails;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
