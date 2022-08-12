package com.se.songs.model;


public enum Type {
    T1("T-One"),
    T2("T-Two");

    private final String description;

    private Type(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}