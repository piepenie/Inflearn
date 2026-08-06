package com.docker.practice.controller;

import com.docker.practice.dto.ValueRequest;
import com.docker.practice.service.RedisSetPracticeService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/practice/redis/sets")
@RequiredArgsConstructor
public class RedisSetPracticeController {

    private final RedisSetPracticeService redisSetPracticeService;

    @PostMapping("/{key}")
    public ResponseEntity<Long> addSetMember(
            @PathVariable String key,
            @RequestBody ValueRequest request
    ) {
        return ResponseEntity.ok(redisSetPracticeService.add(key, request.getValue()));
    }

    @GetMapping("/{key}")
    public ResponseEntity<Set<String>> getSetMembers(@PathVariable String key) {
        return ResponseEntity.ok(redisSetPracticeService.members(key));
    }

    @GetMapping("/{key}/{member}/exists")
    public ResponseEntity<Boolean> isSetMember(
            @PathVariable String key,
            @PathVariable String member
    ) {
        return ResponseEntity.ok(redisSetPracticeService.isMember(key, member));
    }
}
