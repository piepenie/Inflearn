package com.docker.practice.service;

import com.docker.practice.repository.RedisHashPracticeRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisHashPracticeService {

    private static final String KEY_PREFIX = "practice:";

    private final RedisHashPracticeRepository redisHashPracticeRepository;

    public void put(String key, String field, String value) {
        redisHashPracticeRepository.put(redisKey(key), field, value);
    }

    public String get(String key, String field) {
        return redisHashPracticeRepository.get(redisKey(key), field);
    }

    public Map<String, String> entries(String key) {
        return redisHashPracticeRepository.entries(redisKey(key));
    }

    private String redisKey(String key) {
        return KEY_PREFIX + key;
    }
}