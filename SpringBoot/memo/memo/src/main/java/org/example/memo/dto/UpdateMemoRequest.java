package org.example.memo.dto;

import lombok.Getter;

@Getter
public class UpdateMemoRequest {

    private long id;
    private String name;
    private String memo;

}
