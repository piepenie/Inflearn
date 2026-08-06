package com.docker.practice.service;

import com.docker.practice.repository.RedisStringPracticeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisStringPracticeService {

    private static final String KEY_PREFIX = "practice:";

    private final RedisStringPracticeRepository redisStringPracticeRepository;

    public void set(String key, String value) {
        redisStringPracticeRepository.set(redisKey(key), value);
    }

    public String get(String key) {
        return redisStringPracticeRepository.get(redisKey(key));
    }

    public List<String> multiGet(List<String> keys) {
        List<String> redisKeys = keys.stream()
                .map(this::redisKey)
                .toList();
        return redisStringPracticeRepository.multiGet(redisKeys);
    }

    public Long increment(String key) {
        return redisStringPracticeRepository.increment(redisKey(key));
    }

    private String redisKey(String key) {
        return KEY_PREFIX + key;
    }
}