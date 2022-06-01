package com.se.sample.controller.advice;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetail {

    private String field;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String value;
    private String message;

    public ErrorDetail(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
