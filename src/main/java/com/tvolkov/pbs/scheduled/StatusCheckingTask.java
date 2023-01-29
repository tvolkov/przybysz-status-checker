package com.tvolkov.pbs.scheduled;

import com.tvolkov.pbs.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatusCheckingTask {

    private final StatusService statusService;

    @Scheduled(cron = "0 0 * ? * *")
    public void checkStatus() {
        statusService.checkStatus();
    }
}
