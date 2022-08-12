package com.se.sample;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
//                .info(new Info().title("Contact Application API").description(
//                        "This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3."));

                .info(new Info().title("Springp API")
                        .description("Spring  sample application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Spring Wiki Documentation")
                        .url("springwiki.github.org"));
    }

}