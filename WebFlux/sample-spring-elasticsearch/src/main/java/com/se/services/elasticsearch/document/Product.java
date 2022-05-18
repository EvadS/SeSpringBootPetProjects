package com.se.services.elasticsearch.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String id;

    private String name;

    private Category category;


    private double price;

    public enum Category {
        CLOTHES,
        ELECTRONICS,
        GAMES;
    }
}