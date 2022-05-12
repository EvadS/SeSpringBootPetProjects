package com.se.sample;

import org.springframework.core.convert.converter.Converter;

/**
 * @author Skiba Evgeniy
 * @date 03.09.2021
 */

public class StringToEnumConverter implements Converter<String, Modes> {
    @Override
    public Modes convert(String source) {
        return Modes.valueOf(source.toUpperCase());
    }
}
