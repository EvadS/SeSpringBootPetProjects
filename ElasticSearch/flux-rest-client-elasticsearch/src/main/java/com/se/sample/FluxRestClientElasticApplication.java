package com.se.sample;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@OpenAPIDefinition(
        info = @Info(
                title = "Code-First Approach (reflectoring.io)",
                description = "" +
                        "RestHighLevelClient APIs",
                contact = @Contact(
                        name = "evgeniy skiba",
                        url = "",
                        email = "evad-se@mail.com"
                ),
                license = @License(
                        name = "MIT Licence",
                        url = "https://github.com/thombergs/code-examples/blob/master/LICENSE")),
        servers = @Server(url = "http://localhost:8000")
)
public class FluxRestClientElasticApplication {

    public static void main(String[] args) {
        SpringApplication.run(FluxRestClientElasticApplication.class, args);
    }

}
