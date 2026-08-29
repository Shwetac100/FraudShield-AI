package com.fraudshield.user;

import com.fraudshield.common.dto.ApiResponse;
import com.fraudshield.user.dto.UpdateVendorProfileRequest;
import com.fraudshield.user.dto.UserProfileResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserProfileResponse>> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        UserProfileResponse response = userService.getCurrentUserProfile(userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("User profile retrieved successfully", response));
    }

    @PutMapping("/vendor-profile")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<ApiResponse<UserProfileResponse>> updateVendorProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody UpdateVendorProfileRequest request
    ) {
        UserProfileResponse response = userService.updateVendorProfile(userDetails.getUsername(), request);
        return ResponseEntity.ok(ApiResponse.success("Vendor profile updated successfully", response));
    }
}
