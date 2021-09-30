package com.se.sample.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;


//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = { ApplicationConfig.class })
//@WebAppConfiguration

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GreetControllerIntegrationTest {

    @LocalServerPort
    private Integer port;

    @Test
    void demo_test(){
        assertFalse(false);
    }

}