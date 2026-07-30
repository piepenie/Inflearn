package com.jwt.config;

import com.jwt.auth.dto.AuthUser;
import com.jwt.auth.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        // Bearer 토큰이 없는 요청의 허용 여부는 SecurityConfig가 판단한다.
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.substring("Bearer ".length());

        try {
            authenticate(token, request);
        } catch (JwtException | IllegalArgumentException exception) {
            sendUnauthorized(response, "유효하지 않거나 만료된 JWT입니다.");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void authenticate(String token, HttpServletRequest request) {
        Claims claims = jwtUtil.getClaims(token);
        Long userId = Long.parseLong(claims.getSubject());
        String email = claims.get("email", String.class);
        Role role = Role.of(claims.get("role", String.class));

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("JWT에 이메일이 없습니다.");
        }

        // 이후 요청 처리 과정에서 JWT를 다시 파싱하지 않도록 필요한 인증 정보를 저장한다.
        request.setAttribute("userId", userId);
        request.setAttribute("email", email);
        request.setAttribute("role", role);

        JwtAuthenticationToken authentication = new JwtAuthenticationToken(
                new AuthUser(userId, email, role),
                List.of(new SimpleGrantedAuthority(role.getUserRole()))
        );
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    private void sendUnauthorized(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().write(message);
    }
}
