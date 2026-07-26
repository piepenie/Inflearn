package org.example.movies.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateMoiveResponse {

    private final long id;
    private final String title;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public UpdateMoiveResponse(long id, String title, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
