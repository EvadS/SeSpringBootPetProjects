package com.se.product.service.domain;

import com.se.product.service.validation.annotation.NullOrNotBlank;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@Getter
//@Setter
//@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @ManyToMany(mappedBy = "categories")
    Set<Product> products = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NullOrNotBlank(message = "Category name can not be blank")
    private String name;

    @Column(unique = true, name = "code")
    @NullOrNotBlank(message = "Category code can not be blank")
    private String code;

    @Column(unique = true)
    private Long baseCategory;

    public Category() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getBaseCategory() {
        return baseCategory;
    }

    public void setBaseCategory(Long baseCategory) {
        this.baseCategory = baseCategory;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", baseCategory=" + baseCategory +
                '}';
    }
}
