package com.example.demo.config;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(1)
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // 로그인 API는 아직 JWT가 없으므로 필터를 건너뛴다.
        return request.getRequestURI().equals("/auth/signin");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            sendUnauthorized(response, "Authorization 헤더에 JWT가 필요합니다.");
            return;
        }

        String token = authorizationHeader.substring("Bearer ".length());
        Long userId;

        try {
            userId = jwtUtil.getUserId(token);
        }
        catch (JwtException | IllegalArgumentException exception) {
            sendUnauthorized(response, "유효하지 않거나 만료된 JWT입니다.");
            return;
        }

        // ArgumentResolver가 사용할 수 있도록 요청에 사용자 ID를 저장한다.
        request.setAttribute("userId", userId);

        filterChain.doFilter(request, response);
    }

    private void sendUnauthorized(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().write(message);
    }
}
