package com.docker.practice.repository;

import java.time.Duration;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisSetPracticeRepository {

    private static final Duration DEFAULT_TTL = Duration.ofMinutes(10);

    private final StringRedisTemplate stringRedisTemplate;

    public Long add(String key, String member) {
        Long addedCount = stringRedisTemplate.opsForSet().add(key, member);
        stringRedisTemplate.expire(key, DEFAULT_TTL);
        return addedCount;
    }

    public Set<String> members(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }

    public Boolean isMember(String key, String member) {
        return stringRedisTemplate.opsForSet().isMember(key, member);
    }
}