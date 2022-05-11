package com.se.sample;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

public class TestWorkDoOnFinaly {
    private int testNumbers(int value) {
        if (value > 2) {
            throw new IllegalArgumentException("value is too high!");
        }
        return value;
    }

    //doFinally — аналог try-catch-finally
    private Flux<Integer> checkOnErrorMethodTryCatchFinally() {
        Flux.just(1, 2, 3)
                .map(this::testNumbers)
                .doFinally(signalType -> {
                    if (signalType == SignalType.ON_ERROR) {
                        System.out.println("Error signal");
                    } else if (signalType == SignalType.CANCEL) {
                        System.out.println("Cancel signal");
                    }
                })
                .subscribe(value -> System.out.println("Value: " + value),
                        error -> System.out.println("Error: " + error.getMessage()));
        return null;
    }

    public static void main(String[] args) {
        TestWorkDoOnFinaly testWork = new TestWorkDoOnFinaly();
        System.out.println(testWork.checkOnErrorMethodTryCatchFinally());
    }
}
