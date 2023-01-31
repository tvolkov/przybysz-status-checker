package com.tvolkov.pbs.client;

import com.tvolkov.pbs.dto.Application;
import com.tvolkov.pbs.dto.ObtainTokenRequestBody;
import com.tvolkov.pbs.dto.PrzybyszTokenResponse;
import com.tvolkov.pbs.dto.Stage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "przybysz",
        url = "${przybysz.baseUrl}"
)
public interface PrzybyszApiClient {
    @PostMapping(path = "/token/obtain", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Optional<PrzybyszTokenResponse> authenticate(@RequestBody ObtainTokenRequestBody obtainTokenRequestBody);

    @GetMapping(path = "/dict/stages", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Stage> getStages(@RequestHeader("Authorization") String bearerToken);

    @GetMapping(path = "/applications/proxy?pagination=false&status=3", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Application> getApplications(@RequestHeader("Authorization") String bearerToken);
}
