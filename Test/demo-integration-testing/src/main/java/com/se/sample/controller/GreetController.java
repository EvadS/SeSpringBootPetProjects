package com.se.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/greeting")
public class GreetController {

    @GetMapping
    public String index() {
        return "hello";

    }
}
