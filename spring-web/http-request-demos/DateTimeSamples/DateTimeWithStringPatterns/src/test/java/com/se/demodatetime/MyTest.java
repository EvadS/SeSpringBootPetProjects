package com.se.demodatetime;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class MyTest {
    @Test
    public void testJava8DateTimeJackson() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(LocalDate.now()));
    }
}
