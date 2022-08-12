package com.se.sample.rest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.tools.agent.ReactorDebugAgent;

@SpringBootApplication
public class DemoReactorApplication  {

    public static void main(String[] args) {
        ReactorDebugAgent.init();
        SpringApplication.run(DemoReactorApplication.class, args);
    }
}
