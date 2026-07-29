package com.example.demo.auth.controller;

import com.example.demo.auth.annotation.Auth;
import com.example.demo.config.JwtUtil;
import com.example.demo.auth.dto.AuthUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        // 로그인에 성공해 사용자 ID가 1이라고 가정한다.
        return jwtUtil.createToken(999L);
    }

    @GetMapping("/auth/test")
    public String auth(@Auth AuthUser authUser) {
        log.info("인증된 사용자 ID: {}", authUser.getId());
        return "authUser.getId(): " + authUser.getId();
    }
}
