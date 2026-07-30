package com.jwt.auth.controller;

import com.jwt.auth.dto.AuthUser;
import com.jwt.auth.enums.Role;
import com.jwt.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthTestController {

    private final JwtUtil jwtUtil;

    @PostMapping("/auth/signin")
    public String signin() {
        // 로그인에 성공해 사용자 ID가 333이라고 가정한다.
        return jwtUtil.createToken(333L, "user@example.com", Role.ROLE_USER);
    }

    @GetMapping("/auth/test")
    public AuthUser auth(@AuthenticationPrincipal AuthUser authUser) {
        log.info(
                "인증된 사용자 ID: {}, email: {}, role: {}",
                authUser.getId(),
                authUser.getEmail(),
                authUser.getRole()
        );
        return authUser;
    }
}
