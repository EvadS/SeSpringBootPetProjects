package com.se.sample;

import reactor.core.publisher.Flux;

public class CodeSnippletsTest {

    public static void main(String[] args) {
        create1();
        
    }

    private static void create1() {
        Flux<Object> empty = Flux.empty();
         Flux<String> strFlux =  Flux.empty();
    }
}
