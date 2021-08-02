package com.se.product.service.mapper;

import com.se.product.service.domain.Product;
import com.se.product.service.domain.specification.ProductSearch;
import com.se.product.service.model.ProductItemResponse;
import com.se.product.service.model.payload.PagedProductSearchRequest;
import com.se.product.service.model.payload.ProductRequest;
import com.se.product.service.model.payload.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 *
 */
@Mapper(uses = {PriceMapper.class})
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "prices", ignore = true),
            @Mapping(target = "categories", ignore = true),
            @Mapping(target = "id", ignore = true)
    })
    Product toProduct(ProductRequest productRequest);


    // TODO: implement full product response
    @Mappings({
            @Mapping(target = "product.name", source = "name"),
    })
    ProductResponse toProductRepository(Product product);


    @Mappings({
            @Mapping(target = "price", source = "price"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "cost", source = "cost"),

            @Mapping(target = "categoryCode", source = "categoryCode"),
            @Mapping(target = "categoryName", source = "categoryCode"),

            @Mapping(target = "dateFrom", source = "dateFrom"),
            @Mapping(target = "dateTo", source = "dateTo")
    })
    ProductSearch toProductSearch(PagedProductSearchRequest searchRequest);


    @Mappings({
            @Mapping(target = "name", source = "name"),
      })
    ProductItemResponse toProductItemResponse(Product product);
}
