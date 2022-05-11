package com.se.sample;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

public class ErrorDEmo {


    //doOnError  префикс doOn,

    //Обработка ошибок
    //onErrorReturn  вернуть значение по умолчанию в случае ошибки.
    //onErrorResume — восстановить и продолжить работу (выполнить метод резервного копирования.

    public static void main(String[] args) {
        //hasElements
        demoHasElements();

        Flux<String> flux = Flux
                .<String>error(new IllegalArgumentException())
                .doOnError(System.out::println)
        ;

        defaultIfEmptyDemo();

        Flux.empty()
                .switchIfEmpty(Flux.range(1,3))
                .subscribe(System.out::println);


        demo_onErrorReturn();

        demo_doOnError();

        demo_doFinally();
    }

    private static void demo_doFinally() {
        Flux.just(1, 2, 3)
                .map(i -> testNumbers(i))
                .doFinally(signalType -> {
                    if (signalType == SignalType.ON_ERROR) {
                        System.out.println("Error signal");
                    } else if (signalType == SignalType.CANCEL) {
                        System.out.println("Cancel signal");
                    }
                })
                .subscribe(value -> System.out.println("Value: " + value),
                        error -> System.out.println("Error: " +
                                error.getMessage()));
    }

    private static void demo_onErrorReturn() {
        Flux.just(1, 2, 3)
                .map(i -> testNumbers(i))
                .onErrorReturn(100)
                .subscribe(value -> System.out.println("Value: " + value));
    }

    private static void demo_doOnError() {
        Flux.just(1, 2, 3)
                .map(i -> testNumbers(i))
                .doOnError(e -> {
                    // to do something
                    System.out.println("Log error");
                })
                .subscribe(value -> System.out.println("Value: " + value));
    }

    private static void demoHasElements() {
        Flux<Integer> oddFlux = Flux.just(1, 3);
        oddFlux.hasElements()
                .subscribe(value -> System.out.println("true/false: " + value));
    }


    private static int testNumbers(int value) {
        if (value > 2) {
            throw new IllegalArgumentException("value is too high!");
        }
        return value;
    }




    private static void defaultIfEmptyDemo() {
        //defaultIfEmpty
        Mono.empty()
                .defaultIfEmpty("No such elements!")
                .subscribe(System.out::println);

        Flux.range(1, 6)
                .defaultIfEmpty(222)
                .subscribe(System.out::println);
    }
}
