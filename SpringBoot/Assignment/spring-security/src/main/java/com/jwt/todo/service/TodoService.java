package com.jwt.todo.service;

import com.jwt.auth.dto.AuthUser;
import com.jwt.todo.dto.*;
import com.jwt.todo.entity.Todo;
import com.jwt.todo.repository.TodoRepository;
import com.jwt.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public TodoCreateResponse save(AuthUser authUser, TodoCreateRequest request) {
        User user = User.fromAuthUser(authUser);
        Todo todo = new Todo(
                request.getContent(),
                user
        );
        todoRepository.save(todo);
        return new TodoCreateResponse(
                todo.getId(),
                todo.getContent()
        );
    }

    @Transactional(readOnly = true)
    public List<TodoGetResponse> getAll() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream()
                .map(todo -> new TodoGetResponse(
                        todo.getId(),
                        todo.getContent()
                )).toList();
    }

    @Transactional(readOnly = true)
    public TodoGetResponse getOne(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalStateException("할 일이 없어요.")
        );
        return new TodoGetResponse(
                todo.getId(),
                todo.getContent()
        );
    }

    @Transactional
    public TodoUpdateResponse update(AuthUser authUser, Long todoId, TodoUpdateRequest request) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalStateException("할 일이 없어요.")
        );
        boolean equals = todo.getUser().getId().equals(authUser.getId());
        // 작성자가 일치하지 않는다면
        if (!equals) {
            throw new IllegalStateException("작성자가 일치하지 않습니다.");
        }

        // 작성자가 일치한다면
        todo.update(request.getContent());
        return new TodoUpdateResponse(
                todo.getId(),
                todo.getContent()
        );
    }

    @Transactional
    public void delete(AuthUser authUser, Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalStateException("할 일이 없어요.")
        );
        boolean equals = todo.getUser().getId().equals(authUser.getId());
        // 작성자가 일치하지 않는다면
        if (!equals) {
            throw new IllegalStateException("작성자가 일치하지 않습니다.");
        }

        // 작성자가 일치한다면
        todoRepository.deleteById(todoId);
    }
}
