package com.se.sample.springboottetinggradle.web;



import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class UserResource {

    @NotNull
    private final String name;

    @NotNull
    private final String email;

    private LocalDateTime registrationDate;

    public UserResource(
            @JsonProperty("name") String name,
            @JsonProperty("email") String email) {
        this.name = name;
        this.email = email;
        this.registrationDate = null;
    }
}
