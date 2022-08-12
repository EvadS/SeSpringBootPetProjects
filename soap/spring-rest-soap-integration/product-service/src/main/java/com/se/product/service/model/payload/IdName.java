package com.se.product.service.model.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdName {

    @Schema(name ="id", description = "unique identifier")
    private Long id;

    @Schema(name ="name", description = "The name of item")
    private String name;
}