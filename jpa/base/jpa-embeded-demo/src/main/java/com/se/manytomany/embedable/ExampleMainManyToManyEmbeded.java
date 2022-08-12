package com.se.manytomany.embedable;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan("com.se.manytomany.embedable.entity")
@SpringBootApplication
public class ExampleMainManyToManyEmbeded implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ExampleMainManyToManyEmbeded.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}