package com.se.product.service.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@OpenAPIDefinition
public class OpenApiConfiguration    {

    // TODO:
//    @Value("${application-description}")
//    String appDesciption;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Product management service API")
                .description("This is a sample server Product service")
                .version("0.0.1")
                .contact(apiContact())
                .license(apiLicence());
    }

    private License apiLicence() {
        return new License()
                .name("MIT Licence")
                .url("https://opensource.org/licenses/mit-license.php");
    }

    private Contact apiContact() {
        return new Contact()
                .name("SkiEA")
                .email("Evad.se.dev@gmail.com")
                .url("https://github.com/evads");
    }
}