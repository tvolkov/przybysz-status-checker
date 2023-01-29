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

    private record Application(String applicationId, String applicationStage, String applicationNumber, ClientData clientData) {
    }

    private record ClientData(String firstName, String lastName, String email) {
    }
}
