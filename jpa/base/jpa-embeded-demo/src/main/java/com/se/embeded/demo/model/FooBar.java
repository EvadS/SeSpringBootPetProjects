package com.se.embeded.demo.model;


import javax.persistence.*;


@Entity
public class FooBar {
    @EmbeddedId
    private PrimaryKey compositeKey;

    @Column(name = "value")
    public int value;

    public FooBar() {
    }

    public PrimaryKey getCompositeKey() {
        return compositeKey;
    }

    public void setCompositeKey(PrimaryKey compositeKey) {
        this.compositeKey = compositeKey;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}