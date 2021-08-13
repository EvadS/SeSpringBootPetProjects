package com.se.product.service.domain;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Owner   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "OWNER_2_CATS",
            joinColumns = @JoinColumn(name = "OWNER_ID"),
            inverseJoinColumns = @JoinColumn(name = "CAT_ID"))
    @OrderColumn(name = "order_column")
    private List<Cat> cats = Lists.newArrayList();

    public Owner() {
    }

    public Owner(String name) {
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

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }
}