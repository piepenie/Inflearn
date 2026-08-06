package com.docker.practice.repository;

import java.time.Duration;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisHashPracticeRepository {

    private static final Duration DEFAULT_TTL = Duration.ofMinutes(10);

    private final StringRedisTemplate stringRedisTemplate;

    public void put(String key, String field, String value) {
        stringRedisTemplate.<String, String>opsForHash().put(key, field, value);
        stringRedisTemplate.expire(key, DEFAULT_TTL);
    }

    public String get(String key, String field) {
        return stringRedisTemplate.<String, String>opsForHash().get(key, field);
    }

    public Map<String, String> entries(String key) {
        return stringRedisTemplate.<String, String>opsForHash().entries(key);
    }
}