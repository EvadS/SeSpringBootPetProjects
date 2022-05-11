package com.se.sample;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class DemoMap {

    public static void main(String[] args) {
        //testMap();

        // demoFlatMap();

        //zip
        //joinFlux();

        Flux<String> flux = Flux
                .<String>error(new IllegalArgumentException())
                .doOnError(System.out::println);

        Flux.empty()
                .switchIfEmpty(Flux.range(1,3))
                .subscribe(System.out::println);

        demo_doOnSuccess();
    }

    private static void demo_doOnSuccess() {

    }

    private static void joinFlux() {
        Flux<Integer> just = Flux.just(1, 2, 3);
        Flux<String> just1 = Flux.just("a", "b", "c");

        just1
                .zipWith(just, (word, number) -> word + number)
                .subscribe(System.out::println);

        //сначала шли элементы из первого потока, а затем из второго
        Flux<Integer> oddFlux = Flux.just(1, 3);
        Flux<Integer> evenFlux = Flux.just(2, 4);

        Flux.concat(evenFlux, oddFlux)
                .subscribe(value -> System.out.println("Outer: " + value));
    }

    private static void demoFlatMap() {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f");

        System.out.println("Map ------------------------------");
        Flux.fromIterable(list)
                .map(s -> {
                    return s + "x";
                }).collectList()
                .subscribe(System.out::println);

        System.out.println("Flat map ------------------------------");
        Flux.fromIterable(list)
                .flatMap(s -> {
                    // convert to publisher
                    return Flux.just("x");
                })
                .collect(Collectors.toList())
                .subscribe(System.out::println);
        System.out.println("------------------------------");

        Flux.fromIterable(list)
                .flatMap(s -> {
                    return Flux.just(s + "x");
                })
                .collect(Collectors.toList())
                .subscribe(System.out::println);

        Flux.range(1, 3)
                .startWith(Flux.just(9, 8, 7))
                .subscribe(System.out::println);

        System.out.println("соберает все элементы ------------------------------");
        Flux.just("one", "two", "three", "four", "five")
                .map(s -> s + "_new")
                .log()
                .collect(Collectors.toList())
                .subscribe(System.out::println);
    }

    private static void testMap() {
        // flatmap should return publishers
        Flux<Integer> range = Flux.range(1, 10);

        // преобразовать каждый, вернется новый моно с определенным преобразованием
        Supplier<Function<Integer, Integer>> functionSupplier = () -> s -> s + 1;

        range.map(functionSupplier.get())
                .subscribe(System.out::println);

        range.map(s -> s + 1)
                .subscribe(System.out::println);

        Function<Integer, Integer> integerIntegerFunction = s -> s;
        range.map(integerIntegerFunction);
    }


}
