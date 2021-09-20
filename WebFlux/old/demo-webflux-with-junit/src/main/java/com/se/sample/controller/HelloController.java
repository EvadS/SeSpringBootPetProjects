package com.se.sample.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hello")
public class HelloController {

    /**
     * Provide a fallback handler function on any exception
     */
    @GetMapping
    public Mono<String> helloWorld() {
        return Mono.<String>error(IOException::new)
                .onErrorResume(e -> Mono.just("Error : " + e.getMessage()));
    }

    /**
     * Provide a fallback handler function on {@link IOException}
     */
    @GetMapping("/2")
    public Mono<String> helloWorld2() {
        return Mono.<String>error(IOException::new)
                .onErrorResume(IOException.class, e -> Mono.just("Error : " + e.getMessage()));
    }

    /**
     * Returns a static message on {@link IOException}
     */
    @GetMapping("/test2")
    public Mono<String> helloWorld22() {
        return Mono.<String>error(IOException::new)
                .onErrorReturn(IOException.class, "Error");
    }

    /**
     * Provide a fallback handler function on truthfulness of predicate
     */
    @GetMapping("/3")
    public Mono<String> helloWorld3() {
        return Mono.<String>error(IOException::new)
                .onErrorResume(e -> e instanceof IOException, e -> Mono.just("Error : " + e.getMessage()));
    }

}