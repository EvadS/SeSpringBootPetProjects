package com.se.product.service.domain;

import com.se.product.service.domain.audit.DateAudit;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "product")
public class Product extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    //@NullOrNotBlank(message = "Product name can not be blank")
    private String name;

    // student
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "product_category", joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {
            @JoinColumn(name = "category_id")})
    private Set<Category> categories = new HashSet<>();


    public Product() {
    }

    public void addCategory(Category category) {
        this.categories.add(category);
        category.getProducts().add(this);
    }

    public void removeCategories(Category category) {
        this.getCategories().remove(category);
        category.getProducts().remove(this);
    }

    public void removeCategories() {
        for (Category category : new HashSet<>(categories)) {
            removeCategories(category);
        }
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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
