package com.se.sample.rest.client.exception;

import com.se.sample.rest.client.model.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RestTemplateException extends RuntimeException {

    private ApiError errorResponse;

    public RestTemplateException(ApiError errorResponse) {
        super(errorResponse.getMessage());
        this.errorResponse = errorResponse;
    }

    public ApiError getErrorResponse() {
        return errorResponse;
    }
}