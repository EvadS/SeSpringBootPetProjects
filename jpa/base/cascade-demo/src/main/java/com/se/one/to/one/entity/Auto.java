package com.se.one.to.one.entity;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


// Children
@Entity
@Table(name="autos")
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 65)
    private String model;

    //не несет ответственности за отношения, свяязываем с auto
    @OneToOne (optional=false, mappedBy="auto")
    private RegistrationNumber owner;

    public Auto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public RegistrationNumber getOwner() {
        return owner;
    }

    public void setOwner(RegistrationNumber owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "id=" + id +
                ", model='" + model + '}';
    }
}
