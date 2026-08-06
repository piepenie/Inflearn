package com.docker.practice.controller;

import com.docker.practice.service.RedisTtlPracticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/practice/redis/ttl")
@RequiredArgsConstructor
public class RedisTtlPracticeController {

    private final RedisTtlPracticeService redisTtlPracticeService;

    @GetMapping("/{key}")
    public ResponseEntity<Long> getTtl(@PathVariable String key) {
        return ResponseEntity.ok(redisTtlPracticeService.getTtl(key));
    }
}
