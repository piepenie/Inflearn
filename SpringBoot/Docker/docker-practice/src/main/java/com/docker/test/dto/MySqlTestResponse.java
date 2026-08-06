package com.docker.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MySqlTestResponse {

    private Long studentId;
    private String studentName;
}
