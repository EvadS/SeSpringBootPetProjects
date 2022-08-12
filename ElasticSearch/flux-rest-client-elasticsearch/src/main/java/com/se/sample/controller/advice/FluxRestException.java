package com.se.sample.controller.advice;

public class FluxRestException extends RuntimeException {

    public FluxRestException(String message) {
        super(message);
    }
}