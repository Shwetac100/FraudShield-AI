package com.fraudshield.user;

import com.fraudshield.common.dto.ApiResponse;
import com.fraudshield.user.dto.VendorDashboardSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vendor")
@RequiredArgsConstructor
public class VendorDashboardController {

    private final VendorDashboardService vendorDashboardService;

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<ApiResponse<VendorDashboardSummary>> getVendorDashboard(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        VendorDashboardSummary summary = vendorDashboardService.getVendorDashboardSummary(userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("Vendor dashboard summary retrieved successfully", summary));
    }
}
