package com.se.one.to.one.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// Parent
@Entity
@Table(name="registration_number")
public class RegistrationNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 65)
    @Column(name = "code")
    private String code;

    @OneToOne (optional=true, cascade = CascadeType.ALL)
    @JoinColumn (name="auto_id")
    private  Auto auto;


    public RegistrationNumber() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    @Override
    public String toString() {
        return "RegistrationNumber{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", auto=" + auto +
                '}';
    }
}
