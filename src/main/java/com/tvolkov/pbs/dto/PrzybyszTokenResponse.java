package com.tvolkov.pbs.dto;

public record PrzybyszTokenResponse(String token, Data data) {

    public static record Data(String id) {
    }
}
