package com.docker.practice.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class KeysRequest {

    private List<String> keys;
}