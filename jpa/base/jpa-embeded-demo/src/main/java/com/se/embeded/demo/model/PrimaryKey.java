package com.se.embeded.demo.model;

import com.se.embeded.demo.converter.FooBarTypeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PrimaryKey implements Serializable {
    @Column(name = "id")
    private String id;

    @Column(name = "fooBarType")
    @Convert(converter = FooBarTypeConverter.class)
    private FooBarType fooBarType;

    public PrimaryKey() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FooBarType getFooBarType() {
        return fooBarType;
    }

    public void setFooBarType(FooBarType fooBarType) {
        this.fooBarType = fooBarType;
    }
}