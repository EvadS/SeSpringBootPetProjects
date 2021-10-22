package com.se.sample.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.se.sample.entity.base.AuditMetadata;
import com.se.sample.helper.Indices;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
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

    //in data base 1634823963976
    @LastModifiedDate
    @Field(type = FieldType.Long)
    private LocalDateTime updatedAt;


    ///  according to data base format, in data base will stored like "createdDate": "20211021T134603.976Z"
    @CreatedDate
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Instant createdDate;
}