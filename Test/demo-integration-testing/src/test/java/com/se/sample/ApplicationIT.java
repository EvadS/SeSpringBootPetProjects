package com.se.sample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

//@ActiveProfiles("test")
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {
                "server.port=8042",
                "management.server.port=9042",
                "my.custom.property=inlined",
                "spring.main.banner-mode=off"

        })
public class ApplicationIT {


    @Autowired
    private Environment environment;

    @LocalServerPort
    private Integer port;

    @LocalServerPort
    private Integer managementPort;

    @Test
    void printPortsInUse() {
        System.out.println(port);
        System.out.println(managementPort);
    }

    @Test
    void shouldPrintConfigurationValues() {
        System.out.println(environment.getProperty("my.custom.property")); // inlined
        System.out.println(environment.getProperty("spring.application.name")); // integration-test
    }
}
