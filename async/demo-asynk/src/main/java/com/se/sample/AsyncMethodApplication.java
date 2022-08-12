package com.se.sample;


import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
 @EnableAsync
@SpringBootApplication
public class AsyncMethodApplication {


    public static void main(String[] args) {
        SpringApplication.run(AsyncMethodApplication.class, args);
    }

    @Bean("threadPoolTaskExecutor")
    public Executor taskExecutor() {
        final int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("*** Available processors: " + cores);
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(cores);
        executor.setMaxPoolSize(cores);

        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("** GithubLookup-");
        executor.initialize();
        return executor;
    }
}
