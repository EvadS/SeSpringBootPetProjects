package com.se.embeded.demo.converter;

import com.se.embeded.demo.model.FooBarType;

import javax.persistence.AttributeConverter;

@javax.persistence.Converter
public class FooBarTypeConverter implements AttributeConverter<FooBarType, String> {
    @Override
    public String convertToDatabaseColumn(FooBarType t) {
        return t.toString();
    }

    @Override
    public FooBarType convertToEntityAttribute(String s) {
        for (FooBarType value : FooBarType.values()) {
            if (value.name().equalsIgnoreCase(s)) {
                return value;
            }
        }

        throw new IllegalArgumentException("Invalid value for enum: " + s);
    }
}
