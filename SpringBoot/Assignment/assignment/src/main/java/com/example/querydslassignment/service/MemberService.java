package com.example.querydslassignment.service;

import com.example.querydslassignment.entity.Member;
import com.example.querydslassignment.dto.MemberCreateRequest;
import com.example.querydslassignment.dto.MemberResponse;
import com.example.querydslassignment.dto.MemberSearchRequest;
import com.example.querydslassignment.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponse create(MemberCreateRequest request) {
        Member member = memberRepository.save(new Member(request.name(), request.age()));
        return MemberResponse.from(member);
    }

    public List<MemberResponse> search(MemberSearchRequest request) {
        return memberRepository.search(request);
    }
}
