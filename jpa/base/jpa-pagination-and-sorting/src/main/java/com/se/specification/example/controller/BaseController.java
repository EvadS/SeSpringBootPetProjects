package com.se.specification.example.controller;

/**
 * @author Evgeniy Skiba on 20.06.2020
 * @project spring-data-jpa
 */
import com.se.specification.example.service.errors.ErrorResponse;
import org.springframework.http.ResponseEntity;

public class BaseController {

    protected <T> ResponseEntity<T> toResponse(T body) {
        return ResponseEntity
                .ok(body);
    }

    protected <T extends ErrorResponse> ResponseEntity<T> errorToResponse(T error) {
        return ResponseEntity
                .status(error.getStatus())
                .body(error);
    }

}