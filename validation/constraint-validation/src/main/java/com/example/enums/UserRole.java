package com.example.enums;

import java.util.stream.Stream;

public enum UserRole {
    NOT_SET(0),
    ADMIN(1),
    SUPER_ADMIN(2),
    MANAGER(3);

    private int id;

    UserRole(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static UserRole of(int id) {
        return Stream.of(UserRole.values())
                .filter(p -> p.getId() == id)
                .findFirst()
                // .orElse(NOT_SET); /* prefer to use this  */
        .orElse(null);
    }


}
