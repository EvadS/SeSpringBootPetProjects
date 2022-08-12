package com.se.sample.exception;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.validation.FieldError;

@Getter
@Relation(value = "validationError", collectionRelation = "validationErrors")
public class ValidationErrorResource extends RepresentationModel {
    private final String property;
    private final String message;
    private final String invalidValue;

    public ValidationErrorResource(FieldError fieldError) {
        this.property = fieldError.getField();
        this.message = fieldError.getDefaultMessage();
        this.invalidValue = String.valueOf(fieldError.getRejectedValue());
    }
}