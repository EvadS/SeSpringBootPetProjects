package com.se.semple.exception;

/**
 * Created by Evgeniy Skiba on 21.04.21
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyEntityNotFoundException extends RuntimeException {

    public MyEntityNotFoundException(Long id) {
        super("Entity is not found, id="+id);
    }
}