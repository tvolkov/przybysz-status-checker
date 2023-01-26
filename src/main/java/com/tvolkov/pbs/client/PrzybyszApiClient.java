package com.tvolkov.pbs.client;

import com.tvolkov.pbs.dto.ObtainTokenRequestBody;
import com.tvolkov.pbs.dto.PrzybyszTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(
        name = "przybysz",
        url = "${przybysz.baseUrl}"
)
public interface PrzybyszApiClient {
    @PostMapping(path = "/token/obtain", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Optional<PrzybyszTokenResponse> getBalanceForUser(@RequestBody ObtainTokenRequestBody obtainTokenRequestBody);
}
