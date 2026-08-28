package com.fraudshield.auth;

import com.fraudshield.auth.dto.AuthResponse;
import com.fraudshield.auth.dto.LoginRequest;
import com.fraudshield.auth.dto.RegisterRequest;
import com.fraudshield.auth.jwt.JwtService;
import com.fraudshield.auth.jwt.UserPrincipal;
import com.fraudshield.exception.BadRequestException;
import com.fraudshield.user.Role;
import com.fraudshield.user.User;
import com.fraudshield.user.UserRepository;
import com.fraudshield.user.VendorProfile;
import com.fraudshield.user.VendorProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final VendorProfileRepository vendorProfileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email is already registered: " + request.getEmail());
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .role(request.getRole() != null ? request.getRole() : Role.CONSUMER)
                .build();

        User savedUser = userRepository.save(user);

        if (savedUser.getRole() == Role.VENDOR) {
            String businessName = request.getBusinessName() != null && !request.getBusinessName().isBlank()
                    ? request.getBusinessName()
                    : savedUser.getFullName() + "'s Store";

            VendorProfile vendorProfile = VendorProfile.builder()
                    .user(savedUser)
                    .businessName(businessName)
                    .businessAddress(request.getBusinessAddress())
                    .businessLicenseNumber(request.getBusinessLicenseNumber())
                    .build();
            vendorProfileRepository.save(vendorProfile);
        }

        UserPrincipal userPrincipal = new UserPrincipal(savedUser);
        String jwtToken = jwtService.generateToken(userPrincipal);

        return AuthResponse.builder()
                .token(jwtToken)
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .fullName(savedUser.getFullName())
                .role(savedUser.getRole())
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userPrincipal.getUser();
        String jwtToken = jwtService.generateToken(userPrincipal);

        return AuthResponse.builder()
                .token(jwtToken)
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole())
                .build();
    }
}
