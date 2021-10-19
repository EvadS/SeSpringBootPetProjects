package com.example;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class ColdHotPublisher {

    @Test
    public void coldPublisherExample() throws InterruptedException {
        Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(1));
        Thread.sleep(2000);
        intervalFlux.subscribe(i -> System.out.println(String.format("Subscriber A, value: %d", i)));
        Thread.sleep(2000);
        intervalFlux.subscribe(i -> System.out.println(String.format("Subscriber B, value: %d", i)));
        Thread.sleep(3000);
    }

    @Test
    public void hotPublisherExample() throws InterruptedException {
        Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(1));
        //Один из способов создания горячего Publisher -
        ConnectableFlux<Long> intervalCF = intervalFlux.publish();
        //для запуска передачи значений.
        intervalCF.connect();
        Thread.sleep(2000);
        intervalCF.subscribe(i -> System.out.println(String.format("Subscriber A, value: %d", i)));
        Thread.sleep(2000);
        intervalCF.subscribe(i -> System.out.println(String.format("Subscriber B, value: %d", i)));
        Thread.sleep(3000);
    }
}
