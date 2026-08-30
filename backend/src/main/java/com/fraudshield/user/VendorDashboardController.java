package com.fraudshield.user;

import com.fraudshield.common.dto.ApiResponse;
import com.fraudshield.user.dto.VendorDashboardSummary;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vendor")
@Tag(name = "Vendor Dashboard", description = "Endpoints for vendor dashboard analytics and summaries")
@SecurityRequirement(name = "BearerAuthentication")
public class VendorDashboardController {

    private final VendorDashboardService vendorDashboardService;

    public VendorDashboardController(VendorDashboardService vendorDashboardService) {
        this.vendorDashboardService = vendorDashboardService;
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('VENDOR')")
    @Operation(summary = "Get vendor dashboard summary", description = "Requires authentication and ROLE_VENDOR. Retrieves scan metrics, compliance stats, and recent scan history for vendor.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Vendor dashboard summary retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Access Forbidden - requires ROLE_VENDOR", content = @Content)
    })
    public ResponseEntity<ApiResponse<VendorDashboardSummary>> getVendorDashboard(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        VendorDashboardSummary summary = vendorDashboardService.getVendorDashboardSummary(userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("Vendor dashboard summary retrieved successfully", summary));
    }
}
