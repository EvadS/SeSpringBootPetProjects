package com.se.product.service.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import  lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity

public class Cat  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    public Cat() {
    }

    public Cat(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}