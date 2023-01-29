package com.tvolkov.pbs.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.List;

@Getter
public class ApplicationsResponse {

    private final List<Application> applications;

    @JsonCreator
    public ApplicationsResponse(List<Application> applications) {
        this.applications = applications;
    }

    public record Application(String applicationId, String applicationStage, String applicationNumber, ClientData clientData) {
    }

    public record ClientData(String firstName, String lastName, String email) {
    }
}
