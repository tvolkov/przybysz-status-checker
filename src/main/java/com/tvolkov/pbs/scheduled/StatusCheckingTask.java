package com.tvolkov.pbs.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StatusCheckingTask {

    @Scheduled(cron = "0 0 * ? * *")
    public void checkStatus() {

    }
}
