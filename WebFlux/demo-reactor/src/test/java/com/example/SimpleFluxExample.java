package com.example;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * . Введение в Project Reactor
 *  издатель выполнялся в основном потоке так же, как подписчик
 */
public class SimpleFluxExample {

    /**
     * преобразует элементы, создаваемые путем применения синхронной функции к каждому элементу
     */
    @Test
    void mapExample() {
        Flux<String> fluxColors = Flux.just("red", "green", "blue");

        // взыть первый символ и вывести
        fluxColors.map(color -> color.charAt(0))
                .subscribe(System.out::println);
    }

    @Test
    void fluxLogSample(){
        Flux<Integer> intFlux = Flux.fromArray(new Integer[]{1,2,3,4,5,6,7});

        System.out.println("log ->> ");
        //регистрация всех сигналов Reactive Streams, происходящих за кулисами
        intFlux.log().subscribe(System.out::println);

        System.out.println("subscribe ->> ");
        intFlux.subscribe(i-> System.out.println(i));
    }


    /**
     * объединяет несколько источников вместе (ожидая, пока все источники
     * испускают один элемент, и объединяет их в кортеж
     */
    @Test
    void zipExample() {
        Flux<String> fluxFruits = Flux.just("apple", "pear", "plum");
        Flux<String> fluxColors = Flux.just("red", "green", "blue");
        Flux<Integer> fluxAmounts = Flux.just(10, 20, 30);

        Flux.zip(fluxFruits, fluxColors, fluxAmounts).subscribe(System.out::println);
    }

    @Test
    public void onErrorExample() {
        Flux<String> fluxCalc = Flux.just(-1, 0, 1)
                .map(i -> "10 / " + i + " = " + (10 / i));

        fluxCalc.subscribe(value -> System.out.println("Next: " + value),
                error -> System.err.println("Error: " + error));
    }

    /**
     * выдать резервное значение, когда наблюдается ошибка указанного типа
     */
    @Test
    public void onErrorReturnExample() {
        Flux<String> fluxCalc = Flux.just(-1, 0, 1)
                .map(i -> "10 / " + i + " = " + (10 / i))
                .onErrorReturn(ArithmeticException.class, "Division by 0 not allowed");

        fluxCalc.subscribe(value -> System.out.println("Next: " + value),
                error -> System.err.println("Error: " + error));
    }

    @Test
    public void stepVerifierTest() {
        Flux<String> fluxCalc = Flux.just(-1, 0, 1)
                .map(i -> "10 / " + i + " = " + (10 / i));

        StepVerifier.create(fluxCalc)
                //ожидается, что будет выдана одна String
                .expectNextCount(1)
                .expectError(ArithmeticException.class)
                .verify();


    }


}
