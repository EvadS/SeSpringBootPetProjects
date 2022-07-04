package com.se.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoPrometeusApplication {

    @GetMapping("/message")
    public String getMessage() {
        return "Working...!!";
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoPrometeusApplication.class, args);
    }

}
