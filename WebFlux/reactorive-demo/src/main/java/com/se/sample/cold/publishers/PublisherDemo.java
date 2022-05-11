package com.se.sample.cold.publishers;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.util.Arrays;
import java.util.stream.Collectors;


public class PublisherDemo {

    static  void demo1(){

        Publisher<Integer> intPublisher =  Flux.fromArray(new Integer[]{ 1,2,3,4,5,6,7 });

        StepVerifier.create(intPublisher)
                .expectNext(1,2,3,4,5,6,7)
                .verifyComplete();

        Flux<Integer> from = Flux.from(intPublisher);

        Flux.just("1,2,3", "4,5,6")
                .flatMap(i -> Flux.fromIterable(Arrays.asList(i.split(","))))
                .collect(Collectors.toList())
                .subscribe(System.out::println);

        Mono<Integer> monoFromFlux = Mono.from(intPublisher);
        Mono<Integer> integerMono = Mono.fromDirect(intPublisher);
        Flux flux3 = Flux.range(5, 3);


    }

    static  void demoMap(){
        //map — преобразует элементы, испускаемые этим потоком,
        // применяя синхронную функцию к каждому элементу

        Flux flux3 = Flux.range(5, 3);


        Flux.just(1, 2, 3)
                .map(s -> s + 1)
                .subscribe(System.out::println);

        /**
         *  convert async to Publisher-s,
         *  then join this inner Publisher-s to one stream
         */
        Flux.just("1,2,3", "4,5,6")
                .flatMap(i -> Flux.fromIterable(Arrays.asList(i.split(","))))
                .collect(Collectors.toList())
                .subscribe(System.out::println);
    }


    public static void main(String[] args) {
        demo1();
    }
}
