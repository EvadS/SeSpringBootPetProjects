package com.example.soap.additional.error;

public class CustomEndpointNotFoundException extends Exception {
    public CustomEndpointNotFoundException(String message) {
        super(message);
    }
}
