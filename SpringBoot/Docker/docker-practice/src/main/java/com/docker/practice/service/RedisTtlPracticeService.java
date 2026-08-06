package com.docker.practice.service;

import com.docker.practice.repository.RedisTtlPracticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisTtlPracticeService {

    private static final String KEY_PREFIX = "practice:";

    private final RedisTtlPracticeRepository redisTtlPracticeRepository;

    public Long getTtl(String key) {
        return redisTtlPracticeRepository.getTtl(KEY_PREFIX + key);
    }
}
