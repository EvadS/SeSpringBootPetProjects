package com.se.songs.enums;


import com.fasterxml.jackson.annotation.JsonFormat;


import java.util.stream.Stream;

// from monsta
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AdminUserRole {
    NOT_SET(0, "NOT_SET"),
    ADMIN(1, "ADMIN"),
    SUPER_ADMIN(2, "SUPER_ADMIN");


    private int id;

    private String name;

    AdminUserRole(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static AdminUserRole of(int id) {
        return Stream.of(AdminUserRole.values())
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(NOT_SET);
    }

}