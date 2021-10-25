package com.se.sample.mapper;

import com.se.sample.model.One;
import com.se.sample.model.OneDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface OneMapper {

    @Mapping(target="id", source="one.id")
    OneDto createOne (One one);

}