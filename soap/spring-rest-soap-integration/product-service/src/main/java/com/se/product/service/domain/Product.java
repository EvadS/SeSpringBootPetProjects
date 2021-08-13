package com.se.product.service.domain;

import com.se.product.service.domain.audit.DateAudit;
import com.se.product.service.validation.annotation.NullOrNotBlank;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


@Entity
@Table(name = "product")
public class Product extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NullOrNotBlank(message = "Product name can not be blank")
    private String name;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "product_category", joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {
            @JoinColumn(name = "category_id")})
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "product_price",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "price_id")})
    private Set<Price> prices = new HashSet<>();

    public Product() {
    }

    public void addCategory(Category category) {
        this.categories.add(category);
        category.getProducts().add(this);
    }

    public void addPrice(Price price) {
        this.prices.add(price);
        price.getProducts().add(this);
    }

    public void removePrices(Price price) {
        this.getPrices().remove(price);
        price.getProducts().remove(this);
    }

    public void removeAllCategories(Category category) {
        this.getCategories().remove(category);
        category.getProducts().remove(this);
    }

    public void removeAllCategories() {
        for (Category category : new HashSet<>(categories)) {
            removeAllCategories(category);
        }
    }

    public void removeAllPrices() {
        for (Price price : new HashSet<>(prices)) {
            removePrices(price);
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

    public Set<Price> getPrices() {
        return prices;
    }

    public void setPrices(Set<Price> prices) {
        this.prices = prices;
    }


}
