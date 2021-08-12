package com.se.product.service.model.response;

import com.se.product.service.model.payload.IdName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ProductResponse",
        description = "Provide information about one stored product")
public class ProductResponse {
    @Schema(name = "unique identifier of product")
    private Long id;

    @Schema(name = "name of product")
    private String name;

    @Schema(name = "Set of product categories")
    private Set<IdName> categories = new HashSet<>();

    @Schema(name = "Set of product prices")
    private Set<IdName> prices = new HashSet<>();
}
