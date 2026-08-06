package com.docker.test.controller;

import com.docker.test.dto.MySqlTestResponse;
import com.docker.test.dto.RedisTestResponse;
import com.docker.test.dto.TestRequest;
import com.docker.test.service.MySqlTestService;
import com.docker.test.service.RedisTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final MySqlTestService mySqlTestService;
    private final RedisTestService redisTestService;

    @PostMapping("/test/mysql")
    public ResponseEntity<MySqlTestResponse> testMySql(@RequestBody TestRequest request) {
        return ResponseEntity.ok(mySqlTestService.save(request.getName()));
    }

    @GetMapping("/test/mysql/{studentId}")
    public ResponseEntity<MySqlTestResponse> getMySql(@PathVariable Long studentId) {
        return ResponseEntity.ok(mySqlTestService.get(studentId));
    }

    @PostMapping("/test/redis")
    public ResponseEntity<RedisTestResponse> testRedis(@RequestBody TestRequest request) {
        return ResponseEntity.ok(redisTestService.save(request.getName()));
    }

    @GetMapping("/test/redis/{redisKey}")
    public ResponseEntity<RedisTestResponse> getRedis(@PathVariable String redisKey) {
        return ResponseEntity.ok(redisTestService.get(redisKey));
    }
}
