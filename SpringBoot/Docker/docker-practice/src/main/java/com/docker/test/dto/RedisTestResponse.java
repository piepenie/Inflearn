package com.docker.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RedisTestResponse {

    private String redisKey;
    private String redisValue;
}
