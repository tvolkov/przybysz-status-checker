package com.tvolkov.pbs.configuration.properties;

import jakarta.validation.constraints.NotBlank;
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
public class PrzybyszProperties {

    @NotBlank
    private String login;

    @NotBlank
    private String password;

    private String interval = "PT1H";
}
