package com.tvolkov.pbs.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "przybysz")
@Getter
@Setter
@Validated
@Accessors(chain = true)
public class Configuration {
    private String login;
    private String password;
}
