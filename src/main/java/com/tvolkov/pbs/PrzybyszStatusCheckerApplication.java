package com.tvolkov.pbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.tvolkov.pbs.client")
public class PrzybyszStatusCheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrzybyszStatusCheckerApplication.class, args);
    }
}
