package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.tools.agent.ReactorDebugAgent;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class DemoReactorApplication  {

    public static void main(String[] args) {
        ReactorDebugAgent.init();
        SpringApplication.run(DemoReactorApplication.class, args);
    }
}
