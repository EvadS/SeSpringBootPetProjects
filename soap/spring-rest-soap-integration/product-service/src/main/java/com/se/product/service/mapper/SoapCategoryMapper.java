package com.se.product.service.mapper;


import com.se.product.service.model.request.CategoryRequest;
import com.se.product.service.model.response.CategoryResponse;
import com.se.product.service.model.soap.SoapCategoryRequest;
import com.se.product.service.model.soap.SoapCategoryResponse;
import com.se.product.service.model.soap.SoapCreateCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SoapCategoryMapper {

    SoapCategoryMapper MAPPER = Mappers.getMapper(SoapCategoryMapper.class);

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "code", target = "code"),
            @Mapping(source = "baseCategoryId", target = "baseCategory")
    })
    CategoryRequest toCategoryRequest(SoapCategoryRequest request);

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "code", target = "code"),
            @Mapping(source = "baseCategoryId", target = "baseCategory")
    })
    CategoryRequest toCategoryRequestUpdate(SoapCreateCategory request);


    @Mappings({
            @Mapping(source = "categoryResponse.categoryRequest.name", target = "category.name"),
            @Mapping(source = "categoryResponse.categoryRequest.code", target = "category.code"),
            @Mapping(source = "categoryResponse.categoryRequest.baseCategory", target = "category.baseCode"),
            @Mapping(source = "id", target = "category.id")
    })
    SoapCategoryResponse toCategoryResponse(Long id, CategoryResponse categoryResponse);


    // CategorySearch toCategorySearch(SoapPagedCategoryRequest request);


}
