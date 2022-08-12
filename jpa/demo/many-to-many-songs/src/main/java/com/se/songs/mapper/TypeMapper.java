package com.se.songs.mapper;

import com.se.songs.model.Type;
import com.se.songs.model.TypeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface TypeMapper {
    TypeMapper INSTANCE = Mappers.getMapper(TypeMapper.class);

    @Mapping(source = "description", target = "description")
    default TypeDto typeToTypeDto(Type type) {
        return new TypeDto(type.getDescription());
    }
}