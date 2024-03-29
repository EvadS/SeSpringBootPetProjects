package com.se.product.service.exception.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ValidationError",
        description = "Provide ValidationError")
public class ValidationError {
    private String field;
    private String message;

    public ValidationError() {
    }

    public ValidationError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}