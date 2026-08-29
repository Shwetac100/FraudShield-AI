package com.fraudshield.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "vendor_profiles")
public class VendorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private String businessName;

    @Column
    private String businessAddress;

    @Column
    private String businessLicenseNumber;

    @Builder.Default
    @Column(nullable = false)
    private Double qualityRating = 5.0;

    @Builder.Default
    @Column(nullable = false)
    private Integer totalScans = 0;

    @Builder.Default
    @Column(nullable = false)
    private Integer passedScans = 0;

    @Builder.Default
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public VendorProfile() {
    }

    public VendorProfile(Long id, User user, String businessName, String businessAddress, String businessLicenseNumber, Double qualityRating, Integer totalScans, Integer passedScans, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.businessLicenseNumber = businessLicenseNumber;
        this.qualityRating = qualityRating;
        this.totalScans = totalScans;
        this.passedScans = passedScans;
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public static VendorProfileBuilder builder() {
        return new VendorProfileBuilder();
    }

    public static class VendorProfileBuilder {
        private Long id;
        private User user;
        private String businessName;
        private String businessAddress;
        private String businessLicenseNumber;
        private Double qualityRating = 5.0;
        private Integer totalScans = 0;
        private Integer passedScans = 0;
        private LocalDateTime createdAt = LocalDateTime.now();

        VendorProfileBuilder() {
        }

        public VendorProfileBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public VendorProfileBuilder user(User user) {
            this.user = user;
            return this;
        }

        public VendorProfileBuilder businessName(String businessName) {
            this.businessName = businessName;
            return this;
        }

        public VendorProfileBuilder businessAddress(String businessAddress) {
            this.businessAddress = businessAddress;
            return this;
        }

        public VendorProfileBuilder businessLicenseNumber(String businessLicenseNumber) {
            this.businessLicenseNumber = businessLicenseNumber;
            return this;
        }

        public VendorProfileBuilder qualityRating(Double qualityRating) {
            this.qualityRating = qualityRating;
            return this;
        }

        public VendorProfileBuilder totalScans(Integer totalScans) {
            this.totalScans = totalScans;
            return this;
        }

        public VendorProfileBuilder passedScans(Integer passedScans) {
            this.passedScans = passedScans;
            return this;
        }

        public VendorProfileBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public VendorProfile build() {
            return new VendorProfile(id, user, businessName, businessAddress, businessLicenseNumber, qualityRating, totalScans, passedScans, createdAt);
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

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessLicenseNumber() {
        return businessLicenseNumber;
    }

    public void setBusinessLicenseNumber(String businessLicenseNumber) {
        this.businessLicenseNumber = businessLicenseNumber;
    }

    public Double getQualityRating() {
        return qualityRating;
    }

    public void setQualityRating(Double qualityRating) {
        this.qualityRating = qualityRating;
    }

    public Integer getTotalScans() {
        return totalScans;
    }

    public void setTotalScans(Integer totalScans) {
        this.totalScans = totalScans;
    }

    public Integer getPassedScans() {
        return passedScans;
    }

    public void setPassedScans(Integer passedScans) {
        this.passedScans = passedScans;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
