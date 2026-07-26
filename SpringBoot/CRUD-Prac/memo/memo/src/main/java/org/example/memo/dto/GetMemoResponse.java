package org.example.memo.dto;

import lombok.Getter;

@Getter
public class GetMemoResponse {

    private final long id;
    private final String name;
    private final String memo;

    public GetMemoResponse(Long id, String name, String memo) {
        this.id = id;
        this.name = name;
        this.memo = memo;
    }
}
