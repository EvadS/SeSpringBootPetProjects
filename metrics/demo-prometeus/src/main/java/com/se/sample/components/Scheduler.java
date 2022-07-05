package com.se.sample.components;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import io.prometheus.client.Gauge;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Scheduler {

    private final AtomicInteger testGauge;
    private final Counter testCounter;

    private Gauge guage;

    public Scheduler(MeterRegistry meterRegistry) {
        // Counter vs. gauge, summary vs. histogram
        // https://prometheus.io/docs/practices/instrumentation/#counter-vs-gauge-summary-vs-histogram
        testGauge = meterRegistry.gauge("custom_gauge", new AtomicInteger(0));
        testCounter = meterRegistry.counter("custom_counter");




    }

    @Scheduled(fixedRateString = "10000", initialDelayString = "0")
    public void schedulingTask() {
        int randomNumberInRange = Scheduler.getRandomNumberInRange(0, 100);

        log.info("scheduler value:{}", randomNumberInRange);
        testGauge.set(randomNumberInRange);

        testCounter.increment();
    }

    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
