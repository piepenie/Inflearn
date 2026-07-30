package com.jwt.todo.controller;

import com.jwt.auth.dto.AuthUser;
import com.jwt.todo.dto.*;
import com.jwt.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<TodoCreateResponse> create(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody TodoCreateRequest request
    ) {
        return ResponseEntity.ok(todoService.save(authUser, request));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TodoGetResponse>> getAll() {
        return ResponseEntity.ok(todoService.getAll());
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<TodoGetResponse> getOne(@PathVariable Long todoId) {
        return ResponseEntity.ok(todoService.getOne(todoId));
    }

    @PutMapping("/todos/{todoId}")
    public ResponseEntity<TodoUpdateResponse> update(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long todoId,
            @RequestBody TodoUpdateRequest request
    ) {
        return ResponseEntity.ok(todoService.update(authUser, todoId, request));
    }

    @DeleteMapping("/todos/{todoId}")
    public void delete(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long todoId
    ) {
        todoService.delete(authUser, todoId);
    }
}
