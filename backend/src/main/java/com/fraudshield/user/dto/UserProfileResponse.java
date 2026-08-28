package com.fraudshield.user.dto;

import com.fraudshield.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {
    private Long id;
    private String email;
    private String fullName;
    private Role role;
    private LocalDateTime createdAt;
    private VendorProfileDto vendorProfile;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VendorProfileDto {
        private Long id;
        private String businessName;
        private String businessAddress;
        private String businessLicenseNumber;
        private Double qualityRating;
        private Integer totalScans;
        private Integer passedScans;
    }
}
