package com.example;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Skiba Evgeniy
 * @date 03.09.2021
 */

@Component
public class CustomConverter implements Converter<String, Modes> {
    @Override
    public Modes convert(String source) {
        return Modes.valueOf(source.toUpperCase());
    }
}
