package com.docker.practice.service;

import com.docker.practice.repository.RedisSortedSetPracticeRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisSortedSetPracticeService {

    private static final String KEY_PREFIX = "practice:";

    private final RedisSortedSetPracticeRepository redisSortedSetPracticeRepository;

    public Boolean add(String key, String member, double score) {
        return redisSortedSetPracticeRepository.add(redisKey(key), member, score);
    }

    public Set<String> range(String key, long start, long end) {
        return redisSortedSetPracticeRepository.range(redisKey(key), start, end);
    }

    public Long reverseRank(String key, String member) {
        return redisSortedSetPracticeRepository.reverseRank(redisKey(key), member);
    }

    public Double score(String key, String member) {
        return redisSortedSetPracticeRepository.score(redisKey(key), member);
    }

    private String redisKey(String key) {
        return KEY_PREFIX + key;
    }
}