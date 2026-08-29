package com.fraudshield.auth.dto;

import com.fraudshield.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

public class AuthResponse {
    private String token;
    private Long id;
    private String email;
    private String fullName;
    private Role role;

    public AuthResponse() {
    }

    public AuthResponse(String token, Long id, String email, String fullName, Role role) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
    }

    public static AuthResponseBuilder builder() {
        return new AuthResponseBuilder();
    }

    public static class AuthResponseBuilder {
        private String token;
        private Long id;
        private String email;
        private String fullName;
        private Role role;

        AuthResponseBuilder() {
        }

        public AuthResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public AuthResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AuthResponseBuilder email(String email) {
            this.email = email;
            return this;
        }

        public AuthResponseBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public AuthResponseBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public AuthResponse build() {
            return new AuthResponse(token, id, email, fullName, role);
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
