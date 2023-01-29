package com.tvolkov.pbs.dto;

import java.util.List;

public record ApplicationsResponse(List<Application> applications) {
    private record Application(String applicationId, String applicationStage, String applicationNumber,
                               ClientData clientData) {
    }

    private record ClientData(String firstName, String lastName, String email) {
    }
}
