package com.se.sample.configuration;


import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.*;

@Configuration
public class SpringFoxConfig {

    @Autowired
    TypeResolver typeResolver;

    @Bean
    public Docket api() {
        Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(Arrays.asList("application/json"));

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.se.sample.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                .additionalModels(typeResolver.resolve (com.se.sample.exception.ErrorResponse.class) )
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
                .useDefaultResponseMessages(false);
    }

    private ApiInfo getApiInfo() {
        String documentTitle = "Spring catalog API";

        return new ApiInfo(documentTitle, "API Documentation", "0.5.0", null,
                null, null, null, new ArrayList<VendorExtension>());
    }
}
