package com.docker.sample;

import com.docker.sample.properties.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class FileUploadDockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileUploadDockerApplication.class, args);
    }

}
