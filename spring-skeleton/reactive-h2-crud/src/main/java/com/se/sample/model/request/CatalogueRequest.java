package com.se.sample.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;


@Data
@NoArgsConstructor
public class CatalogueRequest {

    @NotEmpty(message = "SKU cannot be null or empty")
    @NonNull
    private String sku;

    @NotEmpty(message = "Name cannot be null or empty")
    @NonNull
    private String name;

    @NotEmpty(message = "Description cannot be null or empty")
    @NonNull
    private String description;

    @NotBlank
    private String category;

    @NotNull(message = "Price cannot be null or empty")
    @NonNull
    private Double price;

    @NotNull(message = "Inventory cannot be null or empty")
    @NonNull
    private Integer inventory;

}