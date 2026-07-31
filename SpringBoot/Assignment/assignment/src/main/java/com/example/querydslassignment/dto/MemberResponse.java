package com.example.querydslassignment.dto;

import com.example.querydslassignment.entity.Member;

public record MemberResponse(Long id, String name, int age) {

    public static MemberResponse from(Member member) {
        return new MemberResponse(member.getId(), member.getName(), member.getAge());
    }
}
