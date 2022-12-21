package com.example.json;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class UserRedis implements Serializable {

    private static final long serialVersionUID = -1541408164202572383L;
    private Long id;

    private String name;

    private Long age;

    public UserRedis() {
    }

    public UserRedis(Long id, String name, Long age) {
        this.id = id;
        this.name = name;
        this.age = age;
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

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
