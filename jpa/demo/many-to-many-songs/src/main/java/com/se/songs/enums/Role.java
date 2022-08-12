package com.se.songs.enums;

public enum Role {
    CUSTOMER(1),
    STAFF_CHIEF(2),
    STAFF(3),
    TRAINEE_SCHOOL(4),
    TRAINEE_REGULAR(5);

    private final int value;

    Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
