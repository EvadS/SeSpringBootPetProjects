package com.se.demodatetime.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.se.demodatetime.model.MyDto;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

import static org.junit.jupiter.api.Assertions.*;

public class MyControllerTest {

    @Test
    public void getMethod() throws Exception {
        MyController controller = new MyController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/myexample/1"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        String json = mvcResult.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        MyDto dto = mapper.readValue(json, MyDto.class);

        assertEquals("name", dto.getName());
    }
}