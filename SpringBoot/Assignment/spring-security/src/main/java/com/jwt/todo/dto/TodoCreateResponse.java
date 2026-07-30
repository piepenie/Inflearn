package com.jwt.todo.dto;

import lombok.Getter;

@Getter
public class TodoCreateResponse {

    private final Long id;
    private final String content;

    public TodoCreateResponse(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
