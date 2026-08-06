package com.docker.test.service;

import com.docker.test.dto.RedisTestResponse;
import com.docker.test.repository.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class RedisTestService {

    private static final String KEY_PREFIX = "redis-test:";

    private final RedisRepository redisRepository;

    public RedisTestResponse save(String name) {
        String redisKey = KEY_PREFIX + name;
        String redisValue = name + "@" + Instant.now();
        redisRepository.save(redisKey, redisValue);
        return new RedisTestResponse(redisKey, redisValue);
    }

    public RedisTestResponse get(String redisKey) {
        String redisValue = redisRepository.findByKey(redisKey);
        if (redisValue == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Redis value not found: " + redisKey);
        }
        return new RedisTestResponse(redisKey, redisValue);
    }
}
