package com.tvolkov.pbs.dto;

public record Application(String applicationId, String applicationStage, String applicationNumber, ClientData clientData) {
}
