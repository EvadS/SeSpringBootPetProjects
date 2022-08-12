package com.se.enums.mapping.model;

import com.se.enums.mapping.converters.PaymentMethodConverter;
import com.se.enums.mapping.enums.PaymentMethod;
import org.springframework.stereotype.Service;

import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class UserBalanceId implements Serializable {
    @NotNull
    private String user_id;

    @NotNull
    @Convert(converter = PaymentMethodConverter.class)
    private PaymentMethod paymentMethod;

    public UserBalanceId() {
    }

    public UserBalanceId(@NotNull String user_id, @NotNull PaymentMethod paymentMethod) {
        this.user_id = user_id;
        this.paymentMethod = paymentMethod;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
