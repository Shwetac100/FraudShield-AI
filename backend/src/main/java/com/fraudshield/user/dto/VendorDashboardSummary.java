package com.fraudshield.user.dto;

import com.fraudshield.scan.dto.ScanResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendorDashboardSummary {

    private String businessName;
    private String businessAddress;
    private String businessLicenseNumber;
    private Double qualityRating;
    private Integer totalScans;
    private Integer passedScans;
    private Integer flaggedScans;
    private Double compliancePercentage;
    private List<ScanResponse> recentScans;
}
