package com.tvolkov.pbs.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public record StagesResponse(@JsonProperty("hydra:member") List<HydraMember> hydraMemberList) {
    private record HydraMember(String id, String name, String synonym) {
    }
}
