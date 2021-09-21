package com.se.sample.models.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponse {

    private String id;

    private Double price;

    private String category;
}
