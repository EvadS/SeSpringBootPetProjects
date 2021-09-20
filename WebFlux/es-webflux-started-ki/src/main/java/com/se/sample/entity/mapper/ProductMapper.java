package com.se.sample.entity.mapper;

import com.se.sample.entity.dto.ProductResponse;
import com.se.sample.entity.dto.UserDto;
import com.se.sample.entity.enums.UserRoles;
import com.se.sample.entity.model.Product;
import com.se.sample.entity.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.HashSet;

@Mapper(imports = {UserRoles.class})
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mappings({
            @Mapping(target="id", source="id"),
            @Mapping(target="price", source="price"),
            @Mapping(target="category", source="category")
    })
    ProductResponse toProductResponse(final Product product);

}
