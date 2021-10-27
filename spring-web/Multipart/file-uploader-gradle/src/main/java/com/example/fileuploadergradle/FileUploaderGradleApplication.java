package com.example.fileuploadergradle;

import com.example.fileuploadergradle.properties.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class FileUploaderGradleApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileUploaderGradleApplication.class, args);
    }

}
