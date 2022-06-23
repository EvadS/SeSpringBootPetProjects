package com.se.sample.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TaskLog {

    private List<String> messages;

    @JsonCreator
    public TaskLog(@JsonProperty("messages") List<String> messages) {
        this.messages = messages;
    }
}
