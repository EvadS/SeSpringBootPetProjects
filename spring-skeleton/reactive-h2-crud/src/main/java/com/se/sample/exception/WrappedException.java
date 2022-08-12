package com.se.sample.exception;

public class WrappedException extends RuntimeException{

    public WrappedException(Throwable e) {
        super(e);
    }
}