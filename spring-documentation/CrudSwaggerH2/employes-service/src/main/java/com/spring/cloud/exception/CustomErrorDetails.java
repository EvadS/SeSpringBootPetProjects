package com.spring.cloud.exception;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "provide additional error information")
public class CustomErrorDetails {
    private String message;
    private int customCode;

    public CustomErrorDetails() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCustomCode() {
        return customCode;
    }

    public void setCustomCode(int customCode) {
        this.customCode = customCode;
    }
}
