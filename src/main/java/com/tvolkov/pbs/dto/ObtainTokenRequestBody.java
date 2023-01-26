package com.tvolkov.pbs.dto;

import lombok.Value;

public record ObtainTokenRequestBody(String login, String password) {
}
