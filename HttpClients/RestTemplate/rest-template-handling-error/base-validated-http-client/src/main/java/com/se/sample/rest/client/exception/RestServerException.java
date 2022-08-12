package com.se.sample.rest.client.exception;

public class RestServerException extends RuntimeException {
    public RestServerException(String errorResponse) {
        super(errorResponse);
    }
}
