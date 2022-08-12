package com.se.sample.mapper;

import com.se.sample.model.CatalogueItem;
import com.se.sample.model.request.CatalogueRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CatalogMapper {

    CatalogMapper INSTANCE = Mappers.getMapper(CatalogMapper.class);


    @Mapping(target = "id", ignore = true)
    @Mapping(target="sku", source="sku")
    @Mapping(target="category", source="category")
    @Mapping(target="description", source="description")
    @Mapping(target="name", source="name")
    @Mapping(target="price", source="price")
    @Mapping(target="inventory", source="inventory")
    CatalogueItem toCatalogueItem (CatalogueRequest request);
}
