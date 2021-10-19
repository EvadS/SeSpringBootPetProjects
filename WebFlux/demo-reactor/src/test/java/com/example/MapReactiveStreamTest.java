package com.example;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

public class MapReactiveStreamTest {

    @Test
    void mapTest1() {

        Flux.range(1, 5).map(data -> data * data)
                .subscribe(System.out::println);

        Flux.range(1, 5).map(data -> data * data)
                .subscribe(data -> System.out.println(data));
    }


    @Test
    void mapTest2() {
        Flux.range(1, 5)
                .map(data -> data.toString() + "Hello")
                .subscribe(System.out::println);
    }


    @Test
    void mapTest3() {
        Flux.range(1, 10)
                .map(data -> data * data)
                .filter(data -> data % 2 == 0)
                .subscribe(System.out::println);
    }


    @Test
    void mapTest4() {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);
        flux.map(data -> data + 2)
                .subscribe(System.out::println);
    }


    @Test
    void mapTest5() {

        Flux<String> flux = Flux.just("Tom", "Jerry");
        flux = flux.map(String::toUpperCase);

        StepVerifier.create(flux)
                .expectNext("TOM", "JERRY")
                .verifyComplete();

    }
    //Combine using zip

    @Test
    public void combineWithZip() {
        Flux<String> f1 = Flux.just("A","B","C");
        Flux<String> f2 = Flux.just("X","Y","Z");
        Flux<Tuple2<String, String>> zip=Flux.zip(f1, f2);

        StepVerifier.create(zip.log())
                .expectNextCount(3)
                .verifyComplete();

    }


    @Test
    public void combineWithZipWith() {

        Flux<String> f1 = Flux.just("A","B","C");
        Flux<String> f2 = Flux.just("X","Y","Z");

        Flux<Tuple2<String, String>> zip=f1.zipWith(f2);

        StepVerifier.create(zip.log())
                .expectNextCount(3)
                .verifyComplete();
    }
}
