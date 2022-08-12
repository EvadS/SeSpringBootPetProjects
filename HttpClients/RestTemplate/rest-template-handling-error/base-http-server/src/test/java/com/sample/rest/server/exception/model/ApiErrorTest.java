package com.sample.rest.server.exception.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.rest.server.model.Car;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ApiErrorTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void write_read_correct() throws IOException {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        String carAsString = objectMapper.writeValueAsString(apiError);

        ApiError readApiError = objectMapper.readValue(carAsString, ApiError.class);

        System.out.println("* Read car " + readApiError);
        Assert.assertEquals(apiError.getTimestamp().getHour(), readApiError.getTimestamp().getHour());

    }

}