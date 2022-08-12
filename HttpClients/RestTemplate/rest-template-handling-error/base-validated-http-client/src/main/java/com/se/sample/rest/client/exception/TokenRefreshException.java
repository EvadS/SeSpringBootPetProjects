package com.se.sample.rest.client.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class TokenRefreshException extends RuntimeException {

    private final String message;

    public TokenRefreshException(String message) {
        super(message);
        this.message = message;
    }
}