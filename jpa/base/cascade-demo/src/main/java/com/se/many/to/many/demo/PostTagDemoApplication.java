package com.se.many.to.many.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PostTagDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostTagDemoApplication.class, args);
    }


    @Bean
    public PostTagStartupBean schedulerRunner() {
        return new PostTagStartupBean();
    }
}
