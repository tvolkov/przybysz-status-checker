package com.tvolkov.pbs;

import com.tvolkov.pbs.client.PrzybyszApiClient;
import com.tvolkov.pbs.dto.ApplicationsResponse;
import com.tvolkov.pbs.dto.ObtainTokenRequestBody;
import com.tvolkov.pbs.dto.PrzybyszTokenResponse;
import com.tvolkov.pbs.dto.StagesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/test/")
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final PrzybyszApiClient przybyszApiClient;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> test() {
        Optional<PrzybyszTokenResponse> authenticate = przybyszApiClient.authenticate(new ObtainTokenRequestBody("215316", "LevPanterovich!@#$5"));
        log.info("Received token: {}", authenticate.get().token());

        var stages = przybyszApiClient.getStages("Bearer " + authenticate.get().token());
        log.info("Received stages: {}", stages);
//        ApplicationsResponse applications = przybyszApiClient.getApplications("Bearer " + authenticate.get().token());
//        log.info("Received applications list: {}", applications);
        return ResponseEntity.ok(stages);
    }
}
