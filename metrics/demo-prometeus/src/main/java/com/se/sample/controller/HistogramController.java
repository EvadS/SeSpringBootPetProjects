package com.se.sample.controller;


import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Histogram;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import static java.lang.Thread.sleep;

@Slf4j
@RestController
public class HistogramController {
    public static final int MAX_DELAY = 5;
    private final Histogram requestDuration;

    public HistogramController(CollectorRegistry collectorRegistry) {
        requestDuration = Histogram.build()
                .name("request_duration")
                .help("Time for HTTP request.")
                .register(collectorRegistry);
    }

    @GetMapping(value = "/wait")
    public String makeMeWait() throws InterruptedException {
        Histogram.Timer timer = requestDuration.startTimer();
        long sleepDuration = Double.valueOf(Math.floor(Math.random() * MAX_DELAY * 1000)).longValue();
        log.info("sleep duration:" + sleepDuration );

        sleep(sleepDuration);
        log.info("get up :" + sleepDuration );
        timer.observeDuration();

        return String.format("I kept you waiting for %s ms!", sleepDuration);
    }
}
