package com.example.querydslassignment.controller;

import com.example.querydslassignment.dto.MemberCreateRequest;
import com.example.querydslassignment.dto.MemberResponse;
import com.example.querydslassignment.dto.MemberSearchRequest;
import com.example.querydslassignment.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponse create(@RequestBody MemberCreateRequest request) {
        return memberService.create(request);
    }

    @GetMapping("/members")
    public List<MemberResponse> search(@ModelAttribute MemberSearchRequest request) {
        return memberService.search(request);
    }
}
