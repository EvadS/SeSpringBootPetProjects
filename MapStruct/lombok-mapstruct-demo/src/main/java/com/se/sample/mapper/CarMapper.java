package com.se.sample.mapper;

import com.se.sample.model.Car;
import com.se.sample.model.CarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper( imports = UUID.class )
public interface CarMapper {
    @Mapping(source = "name", target = "name", defaultExpression = "java(UUID.randomUUID().toString())")
    @Mapping(target = "brand", constant = "BMW")
    @Mapping(source = "price", target = "price", numberFormat = "$#.00")
    @Mapping(source = "manufacturingDate", target = "manufacturingDate", dateFormat = "dd.MM.yyyy")
    Car getModelFromEntity(CarEntity carEntity);

    List<String> getListOfStrings(List<Integer> listOfIntegers);
    List<Car> getCars(List<CarEntity> carEntities);
}