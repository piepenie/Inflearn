package com.docker.practice.service;

import com.docker.practice.repository.RedisSetPracticeRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisSetPracticeService {

    private static final String KEY_PREFIX = "practice:";

    private final RedisSetPracticeRepository redisSetPracticeRepository;

    public Long add(String key, String member) {
        return redisSetPracticeRepository.add(redisKey(key), member);
    }

    public Set<String> members(String key) {
        return redisSetPracticeRepository.members(redisKey(key));
    }

    public Boolean isMember(String key, String member) {
        return redisSetPracticeRepository.isMember(redisKey(key), member);
    }

    private String redisKey(String key) {
        return KEY_PREFIX + key;
    }
}