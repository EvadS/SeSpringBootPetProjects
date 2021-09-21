package com.se.sample.model.enums;

public
    enum Sex {
    NONE(0),
        MAN(1),
        WOMEN(2);

        private  int id;

    Sex(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Sex fromId(int id) {
        for (Sex type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }
}

