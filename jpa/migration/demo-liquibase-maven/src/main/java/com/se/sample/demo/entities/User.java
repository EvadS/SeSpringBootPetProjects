package com.se.sample.demo.entities;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Evgeniy Skiba on 01.04.21
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String userName;

    private int age;


    public User() {
    }

    /**
     *
     * @param userName
     * @param age
     */
    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
