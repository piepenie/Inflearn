package com.jwt.auth.dto;

import com.jwt.auth.enums.Role;
import lombok.Getter;

@Getter
public class AuthUser {

    private final Long id;
    private final String email;
    private final Role role;

    public AuthUser(Long id, String email, Role role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }
}
