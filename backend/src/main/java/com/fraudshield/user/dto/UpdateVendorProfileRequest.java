package com.fraudshield.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVendorProfileRequest {
    @NotBlank(message = "Business name cannot be blank")
    private String businessName;
    private String businessAddress;
    private String businessLicenseNumber;
}
