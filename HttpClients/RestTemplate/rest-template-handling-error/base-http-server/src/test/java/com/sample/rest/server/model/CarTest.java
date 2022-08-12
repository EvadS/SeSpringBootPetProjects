package com.sample.rest.server.model;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class CarTest {

    @Test
    public void is_corect(){
        Assert.assertTrue(true);
    }

    @Test
    public void write_read_correct() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Car car = new Car("yellow", "bmw");
        File file = new File("target/car.json");

        System.out.println("** Write to file : " + file.getCanonicalPath());
        objectMapper.writeValue(file, car);

        String carAsString = objectMapper.writeValueAsString(car);
        Car readCar = objectMapper.readValue(carAsString, Car.class);
        System.out.println("* Read car " + readCar);
        Assert.assertEquals(car, readCar);
        //----------------------
        String jsonString
                = "{ \"color\" : \"yellow\", \"type\" : \"bmw\", \"year\" : \"1970\" }";

        System.out.println("* try to read car " + jsonString);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Car readCar2 = objectMapper.readValue(jsonString, Car.class);
        System.out.println("* Read car " + readCar2);
        Assert.assertEquals(car, readCar2);
    }
}