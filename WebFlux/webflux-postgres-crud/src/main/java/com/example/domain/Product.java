package com.example.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * @author Skiba Evgeniy
 * @date 30.08.2021
 */
@Data
@ToString
public class Product {

    @Id
    private Integer id;
    private String description;
    private Double price;

}
