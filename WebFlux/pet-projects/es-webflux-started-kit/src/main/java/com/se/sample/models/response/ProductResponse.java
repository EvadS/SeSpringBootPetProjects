package com.se.sample.models.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Schema(title = "Provide product full information model")
public class ProductResponse {
    @Schema(title = "unique identifier")
    private String id;

    @Schema(title = "price of product")
    private Double price;

    @Schema(title = "category of product")
    private String category;

    @Schema(title = "name of product")
    private String name;

    @Schema(title = "available count of product")
    private Integer quantity;

    @Schema(title = "description of product")
    private String description;

    @Schema(title = "manufacturer of product")
    private String manufacturer;

    @Schema(title = "created timestamp in ms")
    private Instant createdDate;

    @Schema(title = "last update timestamp in ms")
    private LocalDateTime updatedAt;

}
