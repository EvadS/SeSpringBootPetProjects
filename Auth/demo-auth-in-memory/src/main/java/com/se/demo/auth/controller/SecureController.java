package com.se.demo.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class SecureController {

    private final Logger logger = LoggerFactory.getLogger(SecureController.class);

    @GetMapping("/hello")
    public String hello() {
        logger.info("test() in secure controller");
        return "Hi  on secure controller";
    }

    @GetMapping("/test")
    public String test() {
        logger.info("test() api ");
        return "test";
    }
}