package com.docker.practice.repository;

import java.time.Duration;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisListPracticeRepository {

    private static final Duration DEFAULT_TTL = Duration.ofMinutes(10);

    private final StringRedisTemplate stringRedisTemplate;

    public Long leftPush(String key, String value) {
        Long size = stringRedisTemplate.opsForList().leftPush(key, value);
        stringRedisTemplate.expire(key, DEFAULT_TTL);
        return size;
    }

    public Long rightPush(String key, String value) {
        Long size = stringRedisTemplate.opsForList().rightPush(key, value);
        stringRedisTemplate.expire(key, DEFAULT_TTL);
        return size;
    }

    public String leftPop(String key) {
        return stringRedisTemplate.opsForList().leftPop(key);
    }

    public String rightPop(String key) {
        return stringRedisTemplate.opsForList().rightPop(key);
    }

    public List<String> range(String key, long start, long end) {
        return stringRedisTemplate.opsForList().range(key, start, end);
    }
}