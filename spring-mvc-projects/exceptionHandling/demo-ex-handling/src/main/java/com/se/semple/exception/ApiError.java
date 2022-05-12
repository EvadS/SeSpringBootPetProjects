package com.se.semple.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeniy Skiba on 21.04.21
 */
public class ApiError {
    private String message;
    private String debugMessage;

    private List<String> errors = new ArrayList<>();

    public ApiError() {
    }

    public ApiError(String message, String debugMessage) {
        this.message = message;
        this.debugMessage = debugMessage;
    }

    public ApiError(String message, String debugMessage, List<String> errors) {
        this.message = message;
        this.debugMessage = debugMessage;
        this.errors = errors;
    }



    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }
}
