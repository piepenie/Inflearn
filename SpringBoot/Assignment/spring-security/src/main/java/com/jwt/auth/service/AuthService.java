package com.jwt.auth.service;

import com.jwt.auth.dto.SigninRequest;
import com.jwt.auth.dto.SignupRequest;
import com.jwt.auth.enums.Role;
import com.jwt.config.JwtUtil;
import com.jwt.user.entity.User;
import com.jwt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public void signup(SignupRequest request) {
        boolean existence = userRepository.existsByEmail(request.getEmail());
        // 이메일이 이미 존재하면
        if (existence) {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }

        // 이메일이 존재하지 않으면
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(
                request.getEmail(),
                encodedPassword,
                Role.of(request.getRole().toUpperCase()) // GlobalExceptionHandler에서 명시적으로 커스텀 에러를 핸들링하고 싶어요.
        );
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public String signin(SigninRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 이메일입니다.")
        );
        boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());
        // 비밀번호가 일치하지 않으면
        if (!matches) {
            throw new IllegalStateException("비밀번호가 틀렸습니다. 해킹인가요?");
        }

        // 비밀번호가 일치하면
        return jwtUtil.createToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }
}
