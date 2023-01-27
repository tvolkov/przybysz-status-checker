package com.tvolkov.pbs;

import com.tvolkov.pbs.client.PrzybyszApiClient;
import com.tvolkov.pbs.client.TestClient;
import com.tvolkov.pbs.dto.ObtainTokenRequestBody;
import com.tvolkov.pbs.dto.PrzybyszTokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        return ResponseEntity.ok(authenticate.get().token());
    }
}
