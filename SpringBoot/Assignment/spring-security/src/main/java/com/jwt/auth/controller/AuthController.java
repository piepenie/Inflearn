package com.jwt.auth.controller;

import com.jwt.auth.dto.SigninRequest;
import com.jwt.auth.dto.SignupRequest;
import com.jwt.auth.enums.Role;
import com.jwt.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")

    // 이 API는 ADMIN만 쓸 수 있게 하겠다.
    @Secured(Role.Authority.ADMIN)
    public void signup(
            @RequestBody SignupRequest request
    ) {
        authService.signup(request);
    }

    @PostMapping("/signin")
    public ResponseEntity<Void> signin(
            @RequestBody SigninRequest request
    ) {
        String jwt = authService.signin(request);
        return ResponseEntity.ok().header("Authorization", jwt).build();
    }
}
