package com.docker.practice.controller;

import com.docker.practice.dto.KeysRequest;
import com.docker.practice.dto.ValueRequest;
import com.docker.practice.service.RedisStringPracticeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/practice/redis/strings")
@RequiredArgsConstructor
public class RedisStringPracticeController {

    private final RedisStringPracticeService redisStringPracticeService;

    @PutMapping("/{key}")
    public ResponseEntity<Void> setString(
            @PathVariable String key,
            @RequestBody ValueRequest request
    ) {
        redisStringPracticeService.set(key, request.getValue());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{key}")
    public ResponseEntity<String> getString(@PathVariable String key) {
        return ResponseEntity.ok(redisStringPracticeService.get(key));
    }

    @PostMapping("/mget")
    public ResponseEntity<List<String>> getStrings(@RequestBody KeysRequest request) {
        return ResponseEntity.ok(redisStringPracticeService.multiGet(request.getKeys()));
    }

    @PostMapping("/{key}/increment")
    public ResponseEntity<Long> increment(@PathVariable String key) {
        return ResponseEntity.ok(redisStringPracticeService.increment(key));
    }
}
