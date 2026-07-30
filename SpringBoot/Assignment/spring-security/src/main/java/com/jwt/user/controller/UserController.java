package com.jwt.user.controller;

import com.jwt.auth.dto.AuthUser;
import com.jwt.user.dto.UserGetResponse;
import com.jwt.user.dto.UserUpdateRequest;
import com.jwt.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserGetResponse>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserGetResponse> getOne(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(userService.getOne(userId));
    }

    @GetMapping("/users/me")
    public ResponseEntity<UserGetResponse> me(
            @AuthenticationPrincipal AuthUser authUser
    ) {
        return ResponseEntity.ok(userService.getOne(authUser.getId()));
    }

    @PutMapping("/users/me")
    public ResponseEntity<Void> updateMe(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody UserUpdateRequest request
    ) {
        userService.updateMe(authUser, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/me")
    public void deleteMe(
            @AuthenticationPrincipal AuthUser authUser
    ) {
        userService.deleteMe(authUser);
    }
}
