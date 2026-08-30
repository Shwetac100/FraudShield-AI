package com.fraudshield.auth.dto;

import com.fraudshield.user.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Schema(description = "Request object for user registration")
public class RegisterRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Schema(description = "User email address", example = "vendor@example.com")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Schema(description = "Account password (min 6 characters)", example = "Password123!")
    private String password;

    @NotBlank(message = "Full name is required")
    @Schema(description = "Full name of the user", example = "John Doe")
    private String fullName;

    @NotNull(message = "Role is required")
    @Schema(description = "Role assigned to the user", example = "VENDOR")
    private Role role;

    // Optional vendor business details if role == VENDOR
    @Schema(description = "Business name (required if role is VENDOR)", example = "Fresh Foods Inc")
    private String businessName;

    @Schema(description = "Business physical address", example = "123 Market St, City")
    private String businessAddress;

    @Schema(description = "Business license or registration number", example = "LIC-98765432")
    private String businessLicenseNumber;

    public RegisterRequest() {
    }

    public RegisterRequest(String email, String password, String fullName, Role role, String businessName, String businessAddress, String businessLicenseNumber) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.businessLicenseNumber = businessLicenseNumber;
    }

    public static RegisterRequestBuilder builder() {
        return new RegisterRequestBuilder();
    }

    public static class RegisterRequestBuilder {
        private String email;
        private String password;
        private String fullName;
        private Role role;
        private String businessName;
        private String businessAddress;
        private String businessLicenseNumber;

        RegisterRequestBuilder() {
        }

        public RegisterRequestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public RegisterRequestBuilder password(String password) {
            this.password = password;
            return this;
        }

        public RegisterRequestBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public RegisterRequestBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public RegisterRequestBuilder businessName(String businessName) {
            this.businessName = businessName;
            return this;
        }

        public RegisterRequestBuilder businessAddress(String businessAddress) {
            this.businessAddress = businessAddress;
            return this;
        }

        public RegisterRequestBuilder businessLicenseNumber(String businessLicenseNumber) {
            this.businessLicenseNumber = businessLicenseNumber;
            return this;
        }

        public RegisterRequest build() {
            return new RegisterRequest(email, password, fullName, role, businessName, businessAddress, businessLicenseNumber);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
