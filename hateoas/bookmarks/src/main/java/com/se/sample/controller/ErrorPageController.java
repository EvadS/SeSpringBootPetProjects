package com.se.sample.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ErrorPageController {

    @RequestMapping("/demo")
    public String SpringBootHello() {
        int i=5/0;          // it will throws exception  ArithmeticException: / by zero
        return "demo";
    }

}