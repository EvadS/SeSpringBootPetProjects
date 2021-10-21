package com.se.sample.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.se.sample.entity.base.AuditMetadata;
import com.se.sample.helper.Indices;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@TypeAlias("human")
@Document(indexName = Indices.PERSON_INDEX)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    @Id
    private String id;

    @Field(type = FieldType.Keyword, name = "name")
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be Empty")
    @UniqueElements
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

    private AuditMetadata auditingMetadata;
}