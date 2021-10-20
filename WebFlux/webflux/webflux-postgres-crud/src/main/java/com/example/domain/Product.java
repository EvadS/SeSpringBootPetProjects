package com.example.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Min;

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
    @Min(10)
    private Double price;

}
