package com.tvolkov.pbs.dto;

public record PrzybyszTokenResponse(String token, Data data) {

    public record Data(String id) {
    }
}
