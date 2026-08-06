package com.docker.practice.service;

import com.docker.practice.repository.RedisListPracticeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisListPracticeService {

    private static final String KEY_PREFIX = "practice:";

    private final RedisListPracticeRepository redisListPracticeRepository;

    public Long leftPush(String key, String value) {
        return redisListPracticeRepository.leftPush(redisKey(key), value);
    }

    public Long rightPush(String key, String value) {
        return redisListPracticeRepository.rightPush(redisKey(key), value);
    }

    public String leftPop(String key) {
        return redisListPracticeRepository.leftPop(redisKey(key));
    }

    public String rightPop(String key) {
        return redisListPracticeRepository.rightPop(redisKey(key));
    }

    public List<String> range(String key, long start, long end) {
        return redisListPracticeRepository.range(redisKey(key), start, end);
    }

    private String redisKey(String key) {
        return KEY_PREFIX + key;
    }
}