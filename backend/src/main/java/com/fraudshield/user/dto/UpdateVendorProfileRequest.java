package com.fraudshield.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

public class UpdateVendorProfileRequest {
    @NotBlank(message = "Business name cannot be blank")
    private String businessName;
    private String businessAddress;
    private String businessLicenseNumber;

    public UpdateVendorProfileRequest() {
    }

    public UpdateVendorProfileRequest(String businessName, String businessAddress, String businessLicenseNumber) {
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.businessLicenseNumber = businessLicenseNumber;
    }

    public static UpdateVendorProfileRequestBuilder builder() {
        return new UpdateVendorProfileRequestBuilder();
    }

    public static class UpdateVendorProfileRequestBuilder {
        private String businessName;
        private String businessAddress;
        private String businessLicenseNumber;

        UpdateVendorProfileRequestBuilder() {
        }

        public UpdateVendorProfileRequestBuilder businessName(String businessName) {
            this.businessName = businessName;
            return this;
        }

        public UpdateVendorProfileRequestBuilder businessAddress(String businessAddress) {
            this.businessAddress = businessAddress;
            return this;
        }

        public UpdateVendorProfileRequestBuilder businessLicenseNumber(String businessLicenseNumber) {
            this.businessLicenseNumber = businessLicenseNumber;
            return this;
        }

        public UpdateVendorProfileRequest build() {
            return new UpdateVendorProfileRequest(businessName, businessAddress, businessLicenseNumber);
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
}
