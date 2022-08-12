package com.se.sample;

import com.se.sample.configuration.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class CrudCatalogueApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrudCatalogueApplication.class, args);
    }
}
