package com.tvolkov.pbs.dto;

public class PrzybyszTokenResponse {

    String token;
    Data data;

    public static record Data(String id) {
    }
}
