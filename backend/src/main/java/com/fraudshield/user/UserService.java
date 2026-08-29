package com.fraudshield.user;

import com.fraudshield.exception.ResourceNotFoundException;
import com.fraudshield.user.dto.UpdateVendorProfileRequest;
import com.fraudshield.user.dto.UserProfileResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final VendorProfileRepository vendorProfileRepository;

    public UserService(UserRepository userRepository, VendorProfileRepository vendorProfileRepository) {
        this.userRepository = userRepository;
        this.vendorProfileRepository = vendorProfileRepository;
    }

    public UserProfileResponse getCurrentUserProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + email));

        UserProfileResponse.VendorProfileDto vendorDto = null;
        if (user.getRole() == Role.VENDOR) {
            vendorDto = vendorProfileRepository.findByUserId(user.getId())
                    .map(v -> UserProfileResponse.VendorProfileDto.builder()
                            .id(v.getId())
                            .businessName(v.getBusinessName())
                            .businessAddress(v.getBusinessAddress())
                            .businessLicenseNumber(v.getBusinessLicenseNumber())
                            .qualityRating(v.getQualityRating())
                            .totalScans(v.getTotalScans())
                            .passedScans(v.getPassedScans())
                            .build())
                    .orElse(null);
        }

        return UserProfileResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .vendorProfile(vendorDto)
                .build();
    }

    @Transactional
    public UserProfileResponse updateVendorProfile(String email, UpdateVendorProfileRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + email));

        VendorProfile vendorProfile = vendorProfileRepository.findByUserId(user.getId())
                .orElseGet(() -> VendorProfile.builder()
                        .user(user)
                        .businessName(request.getBusinessName())
                        .build());

        vendorProfile.setBusinessName(request.getBusinessName());
        vendorProfile.setBusinessAddress(request.getBusinessAddress());
        vendorProfile.setBusinessLicenseNumber(request.getBusinessLicenseNumber());

        vendorProfileRepository.save(vendorProfile);

        return getCurrentUserProfile(email);
    }
}
