package com.tvolkov.pbs.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.List;

@Getter
public class StagesResponse {

    private final List<Stage> stages;

    @JsonCreator
    public StagesResponse(List<Stage> stages) {
        this.stages = stages;
    }
    public record Stage(int id, String name, String synonym){}
}
