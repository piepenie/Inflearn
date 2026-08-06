package com.docker.practice.controller;

import com.docker.practice.dto.HashFieldRequest;
import com.docker.practice.service.RedisHashPracticeService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/practice/redis/hashes")
@RequiredArgsConstructor
public class RedisHashPracticeController {

    private final RedisHashPracticeService redisHashPracticeService;

    @PutMapping("/{key}")
    public ResponseEntity<Void> putHash(
            @PathVariable String key,
            @RequestBody HashFieldRequest request
    ) {
        redisHashPracticeService.put(key, request.getField(), request.getValue());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{key}/{field}")
    public ResponseEntity<String> getHash(
            @PathVariable String key,
            @PathVariable String field
    ) {
        return ResponseEntity.ok(redisHashPracticeService.get(key, field));
    }

    @GetMapping("/{key}")
    public ResponseEntity<Map<String, String>> getHashEntries(@PathVariable String key) {
        return ResponseEntity.ok(redisHashPracticeService.entries(key));
    }
}
