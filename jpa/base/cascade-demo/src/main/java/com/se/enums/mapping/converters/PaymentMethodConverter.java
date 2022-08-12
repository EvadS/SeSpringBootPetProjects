package com.se.enums.mapping.converters;

import com.se.enums.mapping.enums.PaymentMethod;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PaymentMethodConverter implements AttributeConverter<PaymentMethod, String> {
    @Override
    public String convertToDatabaseColumn(PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            return null;
        }
        return paymentMethod.getName();
    }

    @Override
    public PaymentMethod convertToEntityAttribute(String id) {
        return PaymentMethod.of(id);
    }
}
