package com.se.sample.rest.client.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceUnavailableException extends RuntimeException
{
    public ResourceUnavailableException(String exception) {
        super(exception);
    }
}