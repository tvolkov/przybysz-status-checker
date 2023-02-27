package com.tvolkov.pbs.scheduled;

import com.tvolkov.pbs.service.StatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StatusCheckingTask {

    private final StatusService statusService;

    @Scheduled(fixedRateString = "${przybysz.interval}", initialDelay = 0)
    public void checkStatus() {
        log.info("checking status");
        statusService.checkStatus();
    }
}
