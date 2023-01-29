package com.tvolkov.pbs.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Component
@Accessors(chain = true)
@ConfigurationProperties(prefix = "email")
public class EmailProperties {

    private Smtp smtp;

    @Getter
    @Setter
    public static class Smtp {
        private String from;
        private String fromName;
        private String host;
        private int port;
        private String username;
        private String password;
        private boolean starttlsEnabled;
        private boolean auth;
    }
}
