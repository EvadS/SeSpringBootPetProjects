package com.se.sample;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "RestHighLevelClient demo", version = "1.0", description = "RestHighLevelClient APIs v1.0"))
public class FluxRestClientElasticApplication {

    public static void main(String[] args) {
        SpringApplication.run(FluxRestClientElasticApplication.class, args);
    }

}
