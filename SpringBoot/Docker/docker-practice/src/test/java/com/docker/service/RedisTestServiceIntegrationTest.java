package com.docker.test.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.docker.test.dto.RedisTestResponse;
import com.docker.test.repository.RedisRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisTestServiceIntegrationTest {

    @Autowired
    private RedisTestService redisTestService;

    @Autowired
    private RedisRepository redisRepository;

    @Test
    void redisCanSaveAndReadValue() {
        RedisTestResponse response = redisTestService.save("redis-student");

        assertThat(redisRepository.findByKey(response.getRedisKey())).isEqualTo(response.getRedisValue());
    }
}
