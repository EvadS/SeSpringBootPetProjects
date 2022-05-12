package com.example.filedemo.model;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiError {
    private String message;

    private int status ;

    public ApiError(String message,  int n1) {
        this.message = message;
        this.status  = n1;
    }

    public ApiError(HttpStatus badRequest, String localizedMessage, List<String> errors) {
        this.message= localizedMessage;
        this.status  =badRequest.value();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public int getN1() {
        return status ;
    }

    public void setN1(int n1) {
        this.status  = n1;
    }
}

