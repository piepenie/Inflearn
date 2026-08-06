package com.docker.practice.dto;

import lombok.Getter;

@Getter
public class SortedSetMemberRequest {

    private String member;
    private double score;
}
