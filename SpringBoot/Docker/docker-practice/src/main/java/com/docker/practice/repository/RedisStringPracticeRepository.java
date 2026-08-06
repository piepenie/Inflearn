package com.docker.practice.repository;

import java.time.Duration;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisStringPracticeRepository {

    private static final Duration DEFAULT_TTL = Duration.ofMinutes(10);

    private final StringRedisTemplate stringRedisTemplate;

    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value, DEFAULT_TTL);
    }

    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public List<String> multiGet(List<String> keys) {
        return stringRedisTemplate.opsForValue().multiGet(keys);
    }

    public Long increment(String key) {
        Long value = stringRedisTemplate.opsForValue().increment(key);
        stringRedisTemplate.expire(key, DEFAULT_TTL);
        return value;
    }
}