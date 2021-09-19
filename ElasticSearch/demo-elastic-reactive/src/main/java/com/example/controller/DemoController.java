package com.example.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 *
 * try to understand testing logic
 * @author Skiba Evgeniy
 * @date 10.09.2021
 */

@AllArgsConstructor
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/index")
    public Flux<String> paged() {
        return Flux.just("hello");
    }
}
