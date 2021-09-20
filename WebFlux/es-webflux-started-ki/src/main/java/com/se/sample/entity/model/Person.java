package com.se.sample.entity.model;


import com.se.sample.entity.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Builder
@AllArgsConstructor
@Document(indexName = "persons")
public class Person {

    @Id
    private String id;

    private Sex sex;
    private String firstName;
    private String lastName;
    private String age;
    private String interests;

}