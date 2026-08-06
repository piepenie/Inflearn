package com.docker.practice.repository;

import java.time.Duration;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisSortedSetPracticeRepository {

    private static final Duration DEFAULT_TTL = Duration.ofMinutes(10);

    private final StringRedisTemplate stringRedisTemplate;

    public Boolean add(String key, String member, double score) {
        Boolean added = stringRedisTemplate.opsForZSet().add(key, member, score);
        stringRedisTemplate.expire(key, DEFAULT_TTL);
        return added;
    }

    public Set<String> range(String key, long start, long end) {
        return stringRedisTemplate.opsForZSet().range(key, start, end);
    }

    public Long reverseRank(String key, String member) {
        return stringRedisTemplate.opsForZSet().reverseRank(key, member);
    }

    public Double score(String key, String member) {
        return stringRedisTemplate.opsForZSet().score(key, member);
    }
}