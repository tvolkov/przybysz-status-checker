package com.tvolkov.pbs.configuration.properties;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Valid
    @NotNull
    private Smtp smtp;

    @Getter
    @Setter
    public static class Smtp {
        private String from;
        private String fromName;

        @NotBlank
        private String host;
        @Min(0)
        @Max(65535)
        private int port;
        @NotBlank
        private String username;
        @NotBlank
        private String password;
        private boolean starttlsEnabled;
        private boolean auth;
    }
}
