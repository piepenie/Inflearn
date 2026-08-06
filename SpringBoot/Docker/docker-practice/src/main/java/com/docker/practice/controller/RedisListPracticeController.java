package com.docker.practice.controller;

import com.docker.practice.dto.ValueRequest;
import com.docker.practice.service.RedisListPracticeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/practice/redis/lists")
@RequiredArgsConstructor
public class RedisListPracticeController {

    private final RedisListPracticeService redisListPracticeService;

    @PostMapping("/{key}/left")
    public ResponseEntity<Long> leftPush(
            @PathVariable String key,
            @RequestBody ValueRequest request
    ) {
        return ResponseEntity.ok(redisListPracticeService.leftPush(key, request.getValue()));
    }

    @PostMapping("/{key}/right")
    public ResponseEntity<Long> rightPush(
            @PathVariable String key,
            @RequestBody ValueRequest request
    ) {
        return ResponseEntity.ok(redisListPracticeService.rightPush(key, request.getValue()));
    }

    @PostMapping("/{key}/left/pop")
    public ResponseEntity<String> leftPop(@PathVariable String key) {
        return ResponseEntity.ok(redisListPracticeService.leftPop(key));
    }

    @PostMapping("/{key}/right/pop")
    public ResponseEntity<String> rightPop(@PathVariable String key) {
        return ResponseEntity.ok(redisListPracticeService.rightPop(key));
    }

    @GetMapping("/{key}")
    public ResponseEntity<List<String>> getList(
            @PathVariable String key,
            @RequestParam(defaultValue = "0") long start,
            @RequestParam(defaultValue = "-1") long end
    ) {
        return ResponseEntity.ok(redisListPracticeService.range(key, start, end));
    }
}
