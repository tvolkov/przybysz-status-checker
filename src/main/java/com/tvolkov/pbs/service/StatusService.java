package com.tvolkov.pbs.service;

import com.tvolkov.pbs.client.PrzybyszApiClient;
import com.tvolkov.pbs.configuration.properties.PrzybyszProperties;
import com.tvolkov.pbs.dto.Application;
import com.tvolkov.pbs.dto.ObtainTokenRequestBody;
import com.tvolkov.pbs.dto.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatusService {

    private final PrzybyszProperties przybyszProperties;
    private final PrzybyszApiClient przybyszApiClient;
    private final EmailService emailService;

    private List<Stage> applicationStages = Collections.emptyList();
    private final ThreadLocal<String> tokenThreadLocal = ThreadLocal.withInitial(() -> "");

    private final Map<String, String> applicationStatuses = new HashMap<>();

    public void checkStatus() {
        getAuthToken();
        checkStagesPreloaded();
        updateStatuses();
    }

    private void getAuthToken() {
        log.info("Getting auth token");
        final var token = przybyszApiClient.authenticate(new ObtainTokenRequestBody(przybyszProperties.getLogin(), przybyszProperties.getPassword()))
                .orElseThrow(() -> new RuntimeException("Unable to get auth token"))
                .token();
        log.info("Received auth token");
        tokenThreadLocal.set(token);// todo validate token's 'expires at'
        log.info("Set auth token");
    }

    private void checkStagesPreloaded() {
        log.info("checking pre-defined stages");
        if (applicationStages.isEmpty()) {
            log.info("Loading stages");
            applicationStages = przybyszApiClient.getStages("Bearer " + tokenThreadLocal.get());
            log.info("Loaded stages");
        }
    }

    private void updateStatuses() {
        log.info("Querying application statuses");
        List<Application> applicationsResponse = przybyszApiClient.getApplications("Bearer " + tokenThreadLocal.get());
        log.info("Received application statuses response");
        applicationsResponse
                .forEach(application -> {
                    log.info("Checking status for application {}", application.applicationNumber());
                    if (!applicationStatuses.getOrDefault(application.applicationNumber(), "").equalsIgnoreCase(application.applicationStage())) {
                        log.info("application status has changed from {} to {}", applicationStatuses.get(application.applicationNumber()), application.applicationStage());
                        emailService.sendEmailNotification(application.clientData().email(), "Przybysz application status update", createEmailBody(application));
                        log.info("Sent email");
                        applicationStatuses.put(application.applicationNumber(), application.applicationStage());
                    }
                });
    }

    private String createEmailBody(Application application) {
        return String.format("New status for application %s: %s", application.applicationNumber(), findApplicationStageName(application.applicationStage()));
    }

    private String findApplicationStageName(String applicationStageId) {
        return applicationStages
                .stream()
                .filter(applicationStage -> applicationStage.id() == Integer.parseInt(applicationStageId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Unable to find application stage with id " + applicationStageId))
                .name();
    }

}
