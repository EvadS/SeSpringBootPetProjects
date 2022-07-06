package com.se.sample.controller;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CounterController {
    private final Counter requestCount;
    public CounterController(CollectorRegistry collectorRegistry) {
        requestCount = Counter.build()
                .name("request_count")
                .help("Number of hello requests.")
                .register(collectorRegistry);
    }

    @GetMapping(value = "/hello")
    public String hello() {
        log.info("Said hello");
        requestCount.inc();
        return "Hi!";
    }
}
