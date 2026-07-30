package com.jwt.user.dto;

import lombok.Getter;

@Getter
public class UserGetResponse {

    private final Long id;
    private final String email;
    private final String role;

    public UserGetResponse(Long id, String email, String role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }
}
