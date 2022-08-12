package com.se.mail.mail.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MailDemoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailDemoServerApplication.class, args);
    }

}
