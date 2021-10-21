package com.se.sample.models.mapper;

import com.se.sample.entity.Product;
import com.se.sample.models.response.ProductResponse;
import com.se.sample.models.request.ProductItemResponse;
import com.se.sample.models.request.ProductRequest;
import com.se.sample.models.enums.UserRoles;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import reactor.core.publisher.Mono;

@Mapper(imports = {UserRoles.class})
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mappings({
            @Mapping(target="id", source="id"),
            @Mapping(target="price", source="price"),
            @Mapping(target="category", source="category")
    })
    ProductResponse toProductResponse(final Product product);


    @Mappings({
            @Mapping(target="price", source="price"),
            @Mapping(target="category", source="category")
    })
    Product toProduct(ProductRequest productRequest);


    @Mappings({
            @Mapping(target="id", source="id"),
            @Mapping(target="name", source="name")
    })
    ProductItemResponse toProductItemResponse(Product product);
}
