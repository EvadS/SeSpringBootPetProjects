package com.se.sample.mapper;

import static org.junit.jupiter.api.Assertions.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import com.se.sample.model.Car;
import com.se.sample.model.CarEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class CarMapperTest {
    private CarMapper carMapper = Mappers.getMapper(CarMapper.class);

    @Test
    public void testEntityToModel() {
        CarEntity entity = new CarEntity();
        entity.setPrice(345000);
        entity.setId(1);
        entity.setManufacturingDate(new GregorianCalendar(2015, 3, 5));

        CarEntity entity1 = new CarEntity();
        entity1.setPrice(445000);
        entity1.setId(2);
        entity1.setManufacturingDate(new GregorianCalendar(2015, 3, 5));

        List<CarEntity> carEntities = Arrays.asList(entity, entity1);

        Car model = carMapper.getModelFromEntity(entity);
        assertEquals("$345000,00",model.getPrice());
        assertEquals(entity.getId(), model.getId());

        assertEquals("BMW", model.getBrand());
        assertEquals("05.04.2015", model.getManufacturingDate());

        List<Integer> list = Arrays.asList(1,2,3);
        List<String> listOfStrings = carMapper.getListOfStrings(list);
        List<Car> listOfCars = carMapper.getCars(carEntities);

        assertEquals(3, listOfStrings.size());
        assertEquals(2, listOfCars.size());
    }
}