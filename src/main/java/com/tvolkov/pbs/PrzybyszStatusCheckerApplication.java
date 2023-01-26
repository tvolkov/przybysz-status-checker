package com.tvolkov.pbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PrzybyszStatusCheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrzybyszStatusCheckerApplication.class, args);
    }
}
