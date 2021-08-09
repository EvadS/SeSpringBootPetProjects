package com.se.product.service.domain;

import com.se.product.service.domain.audit.DateAudit;
import com.se.product.service.validation.annotation.NullOrNotBlank;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;




@Entity
@Table(name = "product")
public class Product  extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NullOrNotBlank(message = "Product name can not be blank")
    private String name;

    // student
    @ManyToMany
    @JoinTable(
            name = "category_product",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    Set<Category> productCategories;

    public Product() {
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

    public Set<Category> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(Set<Category> categories) {
        this.productCategories = categories;
    }
}
