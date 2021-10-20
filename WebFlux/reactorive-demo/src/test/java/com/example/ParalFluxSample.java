package com.example;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class ParalFluxSample {

    /**
     * два способа переключения контекста выполнения в реактивной цепочке: publishOn и subscribeOn
     *
     * <b>publishOn(Scheduler scheduler)</b> влияет на выполнение всех последующих операторов
     * (если не указано иное)
     *
     * <b>subscribeOn(Scheduler scheduler)</b> изменяет поток, из которого подписывается вся цепочка операторов,
     * на основе самого раннего вызова subscribeOn в цепочке.
     */
    @Test
    public void publishSubscribeExample() {
        Scheduler schedulerA = Schedulers.newParallel("Scheduler A");
        Scheduler schedulerB = Schedulers.newParallel("Scheduler B");
        Scheduler schedulerC = Schedulers.newParallel("Scheduler C");

        Schedulers.enableMetrics();

        Flux.just(1)
                // поток планировщика A
                .map(i -> {
                    System.out.println("First map: " + Thread.currentThread().getName());
                    return i;
                })
                .subscribeOn(schedulerA)
                .map(i -> {
                    System.out.println("Second map: " + Thread.currentThread().getName());
                    return i;
                })
                // переключаем контекст выполнения на Scheduler B,
                .publishOn(schedulerB)
                .map(i -> {
                    System.out.println("Third map: " + Thread.currentThread().getName());
                    return i;
                })
                .subscribeOn(schedulerC)
                .map(i -> {
                    System.out.println("Fourth map: " + Thread.currentThread().getName());
                    return i;
                })
                // переключает контекст обратно на Планировщик A перед последней операцией map
                .publishOn(schedulerA)
                .map(i -> {
                    System.out.println("Fifth map: " + Thread.currentThread().getName());
                    return i;
                })
                .blockLast();
    }
}
