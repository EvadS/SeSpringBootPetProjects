package com.se.product.service.domain;

import com.se.product.service.validation.annotation.NullOrNotBlank;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NullOrNotBlank(message = "Product name can not be blank")
    private String name;

    @OneToMany(mappedBy = "product"
            ,fetch = FetchType.EAGER )
    private Set<Price> prices = new HashSet<>();

    @OneToMany(mappedBy = "product"
            ,fetch = FetchType.EAGER )
    private Set<Category> categories = new HashSet<>();

    public void addCategory(Category category) {
        categories.add(category);
        category.setProduct(this);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
        category.setProduct(null);
    }

    public void addPrice(Price price) {
        prices.add(price);
        price.setProduct(this);
    }

    public void removePrice(Price comment) {
        prices.remove(comment);
        comment.setProduct(null);
    }
}
