package com.fraudshield.user.dto;

import com.fraudshield.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class UserProfileResponse {
    private Long id;
    private String email;
    private String fullName;
    private Role role;
    private LocalDateTime createdAt;
    private VendorProfileDto vendorProfile;

    public UserProfileResponse() {
    }

    public UserProfileResponse(Long id, String email, String fullName, Role role, LocalDateTime createdAt, VendorProfileDto vendorProfile) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
        this.createdAt = createdAt;
        this.vendorProfile = vendorProfile;
    }

    public static UserProfileResponseBuilder builder() {
        return new UserProfileResponseBuilder();
    }

    public static class UserProfileResponseBuilder {
        private Long id;
        private String email;
        private String fullName;
        private Role role;
        private LocalDateTime createdAt;
        private VendorProfileDto vendorProfile;

        UserProfileResponseBuilder() {
        }

        public UserProfileResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserProfileResponseBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserProfileResponseBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public UserProfileResponseBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public UserProfileResponseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserProfileResponseBuilder vendorProfile(VendorProfileDto vendorProfile) {
            this.vendorProfile = vendorProfile;
            return this;
        }

        public UserProfileResponse build() {
            return new UserProfileResponse(id, email, fullName, role, createdAt, vendorProfile);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public VendorProfileDto getVendorProfile() {
        return vendorProfile;
    }

    public void setVendorProfile(VendorProfileDto vendorProfile) {
        this.vendorProfile = vendorProfile;
    }

    public static class VendorProfileDto {
        private Long id;
        private String businessName;
        private String businessAddress;
        private String businessLicenseNumber;
        private Double qualityRating;
        private Integer totalScans;
        private Integer passedScans;

        public VendorProfileDto() {
        }

        public VendorProfileDto(Long id, String businessName, String businessAddress, String businessLicenseNumber, Double qualityRating, Integer totalScans, Integer passedScans) {
            this.id = id;
            this.businessName = businessName;
            this.businessAddress = businessAddress;
            this.businessLicenseNumber = businessLicenseNumber;
            this.qualityRating = qualityRating;
            this.totalScans = totalScans;
            this.passedScans = passedScans;
        }

        public static VendorProfileDtoBuilder builder() {
            return new VendorProfileDtoBuilder();
        }

        public static class VendorProfileDtoBuilder {
            private Long id;
            private String businessName;
            private String businessAddress;
            private String businessLicenseNumber;
            private Double qualityRating;
            private Integer totalScans;
            private Integer passedScans;

            VendorProfileDtoBuilder() {
            }

            public VendorProfileDtoBuilder id(Long id) {
                this.id = id;
                return this;
            }

            public VendorProfileDtoBuilder businessName(String businessName) {
                this.businessName = businessName;
                return this;
            }

            public VendorProfileDtoBuilder businessAddress(String businessAddress) {
                this.businessAddress = businessAddress;
                return this;
            }

            public VendorProfileDtoBuilder businessLicenseNumber(String businessLicenseNumber) {
                this.businessLicenseNumber = businessLicenseNumber;
                return this;
            }

            public VendorProfileDtoBuilder qualityRating(Double qualityRating) {
                this.qualityRating = qualityRating;
                return this;
            }

            public VendorProfileDtoBuilder totalScans(Integer totalScans) {
                this.totalScans = totalScans;
                return this;
            }

            public VendorProfileDtoBuilder passedScans(Integer passedScans) {
                this.passedScans = passedScans;
                return this;
            }

            public VendorProfileDto build() {
                return new VendorProfileDto(id, businessName, businessAddress, businessLicenseNumber, qualityRating, totalScans, passedScans);
            }
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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
    }
}
