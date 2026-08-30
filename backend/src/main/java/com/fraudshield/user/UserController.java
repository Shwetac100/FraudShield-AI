package com.fraudshield.user;

import com.fraudshield.common.dto.ApiResponse;
import com.fraudshield.user.dto.UpdateVendorProfileRequest;
import com.fraudshield.user.dto.UserProfileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Profile", description = "Endpoints for managing user and vendor profiles")
@SecurityRequirement(name = "BearerAuthentication")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    @Operation(summary = "Get current user profile", description = "Requires authenticated user. Returns profile details of the current authenticated user.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User profile retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<ApiResponse<UserProfileResponse>> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        UserProfileResponse response = userService.getCurrentUserProfile(userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("User profile retrieved successfully", response));
    }

    @PutMapping("/vendor-profile")
    @PreAuthorize("hasRole('VENDOR')")
    @Operation(summary = "Update vendor profile", description = "Requires authentication and ROLE_VENDOR. Updates vendor business details.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Vendor profile updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Access Forbidden - requires ROLE_VENDOR", content = @Content)
    })
    public ResponseEntity<ApiResponse<UserProfileResponse>> updateVendorProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody UpdateVendorProfileRequest request
    ) {
        UserProfileResponse response = userService.updateVendorProfile(userDetails.getUsername(), request);
        return ResponseEntity.ok(ApiResponse.success("Vendor profile updated successfully", response));
    }
}
