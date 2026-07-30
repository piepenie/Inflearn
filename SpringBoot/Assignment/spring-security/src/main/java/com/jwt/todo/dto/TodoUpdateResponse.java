package com.jwt.todo.dto;

import lombok.Getter;

@Getter
public class TodoUpdateResponse {

    private final Long id;
    private final String content;

    public TodoUpdateResponse(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
