package com.se.sample.exception;

import com.se.sample.entity.Speaker;
import org.slf4j.helpers.MessageFormatter;


public class DuplicateEntityException extends RuntimeException {

    public DuplicateEntityException(Class<Speaker> speakerClass, String message) {
        super(MessageFormatter.format("Entity  {} with name '{}' already present in DB.",
                speakerClass.getSimpleName(),
                message).getMessage());
    }
}