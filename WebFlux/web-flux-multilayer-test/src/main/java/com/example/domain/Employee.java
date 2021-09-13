package com.example.domain;

/**
 * @author Skiba Evgeniy
 * @date 10.09.2021
 */
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;



@Data
@NoArgsConstructor
@Document(indexName = "sample")
public class Employee {

    @Id
   private int id;
    private String name;
    private long salary;
}
