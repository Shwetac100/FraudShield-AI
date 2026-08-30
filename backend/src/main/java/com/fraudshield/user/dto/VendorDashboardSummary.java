package com.fraudshield.user.dto;

import com.fraudshield.scan.dto.ScanResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "Vendor dashboard summary statistics and recent history")
public class VendorDashboardSummary {

    @Schema(description = "Business name")
    private String businessName;

    @Schema(description = "Business address")
    private String businessAddress;

    @Schema(description = "Business license number")
    private String businessLicenseNumber;

    @Schema(description = "Vendor quality score rating")
    private Double qualityRating;

    @Schema(description = "Total scans associated with vendor")
    private Integer totalScans;

    @Schema(description = "Number of passed/safe scans")
    private Integer passedScans;

    @Schema(description = "Number of flagged/high risk scans")
    private Integer flaggedScans;

    @Schema(description = "Compliance percentage (passed/total * 100)")
    private Double compliancePercentage;

    @Schema(description = "List of recent scan results")
    private List<ScanResponse> recentScans;

    public VendorDashboardSummary() {
    }

    public VendorDashboardSummary(String businessName, String businessAddress, String businessLicenseNumber, Double qualityRating, Integer totalScans, Integer passedScans, Integer flaggedScans, Double compliancePercentage, List<ScanResponse> recentScans) {
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.businessLicenseNumber = businessLicenseNumber;
        this.qualityRating = qualityRating;
        this.totalScans = totalScans;
        this.passedScans = passedScans;
        this.flaggedScans = flaggedScans;
        this.compliancePercentage = compliancePercentage;
        this.recentScans = recentScans;
    }

    public static VendorDashboardSummaryBuilder builder() {
        return new VendorDashboardSummaryBuilder();
    }

    public static class VendorDashboardSummaryBuilder {
        private String businessName;
        private String businessAddress;
        private String businessLicenseNumber;
        private Double qualityRating;
        private Integer totalScans;
        private Integer passedScans;
        private Integer flaggedScans;
        private Double compliancePercentage;
        private List<ScanResponse> recentScans;

        VendorDashboardSummaryBuilder() {
        }

        public VendorDashboardSummaryBuilder businessName(String businessName) {
            this.businessName = businessName;
            return this;
        }

        public VendorDashboardSummaryBuilder businessAddress(String businessAddress) {
            this.businessAddress = businessAddress;
            return this;
        }

        public VendorDashboardSummaryBuilder businessLicenseNumber(String businessLicenseNumber) {
            this.businessLicenseNumber = businessLicenseNumber;
            return this;
        }

        public VendorDashboardSummaryBuilder qualityRating(Double qualityRating) {
            this.qualityRating = qualityRating;
            return this;
        }

        public VendorDashboardSummaryBuilder totalScans(Integer totalScans) {
            this.totalScans = totalScans;
            return this;
        }

        public VendorDashboardSummaryBuilder passedScans(Integer passedScans) {
            this.passedScans = passedScans;
            return this;
        }

        public VendorDashboardSummaryBuilder flaggedScans(Integer flaggedScans) {
            this.flaggedScans = flaggedScans;
            return this;
        }

        public VendorDashboardSummaryBuilder compliancePercentage(Double compliancePercentage) {
            this.compliancePercentage = compliancePercentage;
            return this;
        }

        public VendorDashboardSummaryBuilder recentScans(List<ScanResponse> recentScans) {
            this.recentScans = recentScans;
            return this;
        }

        public VendorDashboardSummary build() {
            return new VendorDashboardSummary(businessName, businessAddress, businessLicenseNumber, qualityRating, totalScans, passedScans, flaggedScans, compliancePercentage, recentScans);
        }
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

    public Integer getFlaggedScans() {
        return flaggedScans;
    }

    public void setFlaggedScans(Integer flaggedScans) {
        this.flaggedScans = flaggedScans;
    }

    public Double getCompliancePercentage() {
        return compliancePercentage;
    }

    public void setCompliancePercentage(Double compliancePercentage) {
        this.compliancePercentage = compliancePercentage;
    }

    public List<ScanResponse> getRecentScans() {
        return recentScans;
    }

    public void setRecentScans(List<ScanResponse> recentScans) {
        this.recentScans = recentScans;
    }
}
