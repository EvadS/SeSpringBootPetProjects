package com.se.example.firsttestdemo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HelloController.class)
class HelloControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void hello() throws Exception {
        RequestBuilder request  = MockMvcRequestBuilders.get("/hello");
        MvcResult result =  mockMvc.perform(request).andReturn();

        assertEquals("Hello, World", result.getResponse().getContentAsString());
    }

    @Test
    void helloWithName() throws Exception {
        mockMvc.perform(get("/hello?name=Dan"))
                        .andExpect(content().string("Hello, Dan"));
    }
}