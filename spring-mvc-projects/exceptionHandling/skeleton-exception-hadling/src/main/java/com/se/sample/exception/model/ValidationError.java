package com.se.sample.exception.model;

/**
 * Created by Evgeniy Skiba on 21.04.21
 */
public class ValidationError {
    private final String field;
    private final String message;


    public ValidationError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
