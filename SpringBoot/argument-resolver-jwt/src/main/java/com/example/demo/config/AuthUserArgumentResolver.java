package com.example.demo.config;

import com.example.demo.auth.annotation.Auth;
import com.example.demo.auth.dto.AuthUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAuthAnnotation = parameter.hasParameterAnnotation(Auth.class);
        boolean isAuthUserType = parameter.getParameterType().equals(AuthUser.class);

        if (hasAuthAnnotation && !isAuthUserType) {
            throw new IllegalStateException("@Auth는 AuthUser 타입에만 사용할 수 있습니다.");
        }

        if (!hasAuthAnnotation && isAuthUserType) {
            throw new IllegalStateException("AuthUser 타입에는 @Auth가 필요합니다.");
        }

        return hasAuthAnnotation;
    }

    @Override
    public AuthUser resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        if (request == null) {
            throw new IllegalStateException("HTTP 요청을 찾을 수 없습니다.");
        }

        // JwtFilter가 저장한 사용자 ID를 꺼낸다.
        Long userId = (Long) request.getAttribute("userId");

        if (userId == null) {
            throw new IllegalStateException("인증된 사용자 정보를 찾을 수 없습니다.");
        }

        return new AuthUser(userId);
    }
}
