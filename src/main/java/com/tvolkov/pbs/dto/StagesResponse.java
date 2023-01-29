package com.tvolkov.pbs.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class StagesResponse {

    private final List<Stage> stages;

    @JsonCreator
    public StagesResponse(List<Stage> stages) {
        this.stages = stages;
    }
    private record Stage(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("synonym") String synonym){}
}
