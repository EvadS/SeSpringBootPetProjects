package com.se.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration;


@SpringBootApplication(exclude = ErrorWebFluxAutoConfiguration.class)
public class DemoWebfluxRouteJunitApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoWebfluxRouteJunitApplication.class, args);
    }

}
