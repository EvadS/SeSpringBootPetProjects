package com.se.services.elasticsearch.config;


import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi bookGroupApi() {
        return GroupedOpenApi.builder()
                .group("Books")
                .pathsToMatch("/books**")
                .build();
    }

    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
                .group("all")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public GroupedOpenApi profileGroupApi() {
        return GroupedOpenApi.builder()
                .group("Profile")
                .pathsToMatch("/api/v1/profiles**")
                .build();
    }


    @Bean
    public GroupedOpenApi employeeGroupApi() {
        return GroupedOpenApi.builder()
                .group("Employee")
                .pathsToMatch("/swagger-demo/employee/**")
                .addOpenApiCustomiser(getOpenApiCustomiser())
                .build();
    }

    @Bean
    public GroupedOpenApi jobGroupApi() {
        return GroupedOpenApi.builder()
                .group("Department")
                .pathsToMatch("/swagger-demo/department/**")
                .addOpenApiCustomiser(getOpenApiCustomiser())
                .build();
    }

    public OpenApiCustomiser getOpenApiCustomiser() {

        return openAPI -> openAPI.getPaths().values().stream().flatMap(pathItem ->
                pathItem.readOperations().stream())
                .forEach(operation -> {
                    operation.addParametersItem(new Parameter().name("Authorization").in("header").
                            schema(new StringSchema().example("token")).required(true));
                    operation.addParametersItem(new Parameter().name("userId").in("header").
                            schema(new StringSchema().example("test")).required(true));

                });
    }


}
