package com.se.sample.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author Skiba Evgeniy
 * @date 02.09.2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "product", createIndex = true)
public class Product {
    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Keyword)
    private Category category;

    @Field(type = FieldType.Long)
    private double price;

    public enum Category {
        CLOTHES,
        ELECTRONICS,
        GAMES;
    }
}