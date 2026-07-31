package com.example.querydslassignment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSearchRequest {

    private String name;
    private Integer ageGoe;
    private Integer ageLoe;
    private String sort;
}
