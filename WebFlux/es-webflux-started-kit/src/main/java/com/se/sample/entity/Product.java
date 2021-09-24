package com.se.sample.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@Document(indexName = "product-index")
public class Product {

    public static final String PRODUCT_INDEX = "product-index";

    @Id
    private String id;
    @Field(type = FieldType.Keyword, name = "name")

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be Empty")
    private String name;

    @Field(type = FieldType.Double, name = "price")
    private Double price;

    @Field(type = FieldType.Integer, name = "quantity")
    private Integer quantity;

    @Field(type = FieldType.Keyword, name = "category")
    private String category;

    @Field(type = FieldType.Text, name = "desc")
    private String description;

    @Field(type = FieldType.Keyword, name = "manufacturer")
    private String manufacturer;

    @Field(name = "created_at", type = FieldType.Date, format = DateFormat.date_time)
    private Date createdAt = new Date();

}