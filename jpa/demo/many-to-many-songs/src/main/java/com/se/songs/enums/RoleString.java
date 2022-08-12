package com.se.songs.enums;

public enum RoleString {
    CUSTOMER("Customer"),
    STAFF_CHIEF("Staff with authority"),
    STAFF("Regular staff"),
    TRAINEE_SCHOOL("Trainee from school"),
    TRAINEE_REGULAR("Junior Trainee");

    private final String value;

    RoleString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
