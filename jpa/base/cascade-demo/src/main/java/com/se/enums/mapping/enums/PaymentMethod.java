package com.se.enums.mapping.enums;

import java.util.stream.Stream;

public enum PaymentMethod {
    XLM(0, "XLM"),
    MONSTA_COINT(1, "MC"),
    USD(2, "USD");

    private final int id;
    private final String name;

    PaymentMethod(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static PaymentMethod of(String code) {
        return Stream.of(PaymentMethod.values())
                .filter(p -> p.getName().equals(code))
                .findFirst().orElseThrow(() -> new IllegalStateException(String.format("Unsupported type %s.", code)));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
