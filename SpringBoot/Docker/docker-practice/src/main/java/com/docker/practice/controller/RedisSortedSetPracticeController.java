package com.docker.practice.controller;

import com.docker.practice.dto.SortedSetMemberRequest;
import com.docker.practice.service.RedisSortedSetPracticeService;
import java.util.Set;
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
@RequestMapping("/practice/redis/sorted-sets")
@RequiredArgsConstructor
public class RedisSortedSetPracticeController {

    private final RedisSortedSetPracticeService redisSortedSetPracticeService;

    @PostMapping("/{key}")
    public ResponseEntity<Boolean> addSortedSetMember(
            @PathVariable String key,
            @RequestBody SortedSetMemberRequest request
    ) {
        return ResponseEntity.ok(redisSortedSetPracticeService.add(
                key, request.getMember(), request.getScore()));
    }

    @GetMapping("/{key}")
    public ResponseEntity<Set<String>> getSortedSetMembers(
            @PathVariable String key,
            @RequestParam(defaultValue = "0") long start,
            @RequestParam(defaultValue = "-1") long end
    ) {
        return ResponseEntity.ok(redisSortedSetPracticeService.range(key, start, end));
    }

    @GetMapping("/{key}/{member}/rank")
    public ResponseEntity<Long> getSortedSetReverseRank(
            @PathVariable String key,
            @PathVariable String member
    ) {
        return ResponseEntity.ok(redisSortedSetPracticeService.reverseRank(key, member));
    }

    @GetMapping("/{key}/{member}/score")
    public ResponseEntity<Double> getSortedSetScore(
            @PathVariable String key,
            @PathVariable String member
    ) {
        return ResponseEntity.ok(redisSortedSetPracticeService.score(key, member));
    }
}
