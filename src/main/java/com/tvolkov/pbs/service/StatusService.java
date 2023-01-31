package com.tvolkov.pbs.service;

import com.tvolkov.pbs.client.PrzybyszApiClient;
import com.tvolkov.pbs.configuration.properties.PrzybyszProperties;
import com.tvolkov.pbs.dto.ApplicationsResponse;
import com.tvolkov.pbs.dto.ObtainTokenRequestBody;
import com.tvolkov.pbs.dto.StagesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatusService {

    private final PrzybyszProperties przybyszProperties;
    private final PrzybyszApiClient przybyszApiClient;
    private final EmailService emailService;

    private List<StagesResponse.Stage> applicationStages = Collections.emptyList();
    private final ThreadLocal<String> tokenThreadLocal = ThreadLocal.withInitial(() -> "");

    private final Map<String, String> applicationStatuses = Collections.emptyMap();

    public void checkStatus() {
        getAuthToken();
        checkStagesPreloaded();
        updateStatuses();
    }

    private void getAuthToken() {
        final var token = przybyszApiClient.authenticate(new ObtainTokenRequestBody(przybyszProperties.getLogin(), przybyszProperties.getPassword()))
                .orElseThrow(() -> new RuntimeException("Unable to get auth token"))
                .token();
        tokenThreadLocal.set(token);// todo validate token's 'expires at'
    }

    private void checkStagesPreloaded() {
        if (applicationStages.isEmpty()) {
            applicationStages = przybyszApiClient.getStages("Bearer " + tokenThreadLocal.get());
        }
    }

    private void updateStatuses() {
        ApplicationsResponse applicationsResponse = przybyszApiClient.getApplications("Bearer " + tokenThreadLocal.get());
        applicationsResponse.getApplications()
                .forEach(application -> {
                    if (!applicationStatuses.getOrDefault(application.applicationNumber(), "").equalsIgnoreCase(application.applicationStage())) {
                        emailService.sendEmailNotification(application.clientData().email(), "Przybysz application status update", createEmailBody(application));
                    }
                });
    }

    private String createEmailBody(ApplicationsResponse.Application application) {
        return String.format("New status for application %s: %s", application.applicationNumber(), findApplicationStageName(application.applicationId()));
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
