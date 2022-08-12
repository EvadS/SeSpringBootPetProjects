package com.se.sample.exception.error;

/**
 * Created by Evgeniy Skiba on 21.04.21
 */

public class NoSuchElementFoundException extends RuntimeException {

    public NoSuchElementFoundException(String message) {
        super(message);
    }
}