package com.jwt.user.service;

import com.jwt.auth.dto.AuthUser;
import com.jwt.user.dto.UserGetResponse;
import com.jwt.user.dto.UserUpdateRequest;
import com.jwt.user.entity.User;
import com.jwt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserGetResponse> getAll() {
        List<User> users = userRepository.findAll();

        List<UserGetResponse> dtos = new ArrayList<>();
        for (User user : users) {
            UserGetResponse dto = new UserGetResponse(
                    user.getId(),
                    user.getEmail(),
                    user.getRole().toString()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public UserGetResponse getOne(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 유저입니다.")
        );
        return new UserGetResponse(
                user.getId(),
                user.getEmail(),
                user.getRole().toString()
        );
    }

    @Transactional
    public void updateMe(AuthUser authUser, UserUpdateRequest request) {
        // 이거는 됨
        User user = userRepository.findById(authUser.getId()).orElseThrow(
                () -> new IllegalStateException("없는 유저입니다.")
        );
        user.updatePassword(request.getPassword());
    }

    @Transactional
    public void deleteMe(AuthUser authUser) {
        userRepository.deleteById(authUser.getId());
    }
}
