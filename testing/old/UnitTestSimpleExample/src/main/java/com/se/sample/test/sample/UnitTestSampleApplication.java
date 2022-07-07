package com.se.sample.test.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.se.sample.test.sample"})
public class UnitTestSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnitTestSampleApplication.class, args);
    }

}
