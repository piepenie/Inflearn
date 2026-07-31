package com.example.querydslassignment.repository;

import com.example.querydslassignment.dto.MemberSearchRequest;
import com.example.querydslassignment.dto.MemberResponse;

import java.util.List;

public interface MemberQueryRepository {

    List<MemberResponse> search(MemberSearchRequest request);
}
