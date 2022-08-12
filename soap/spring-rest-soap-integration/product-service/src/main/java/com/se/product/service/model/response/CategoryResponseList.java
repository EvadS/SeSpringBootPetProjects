package com.se.product.service.model.response;

import com.se.product.service.model.response.CategoryResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Schema(description = "Provide model with current categories")
public class CategoryResponseList {
    @Builder.Default
    private List<CategoryResponse> categories = new ArrayList<>();
}
