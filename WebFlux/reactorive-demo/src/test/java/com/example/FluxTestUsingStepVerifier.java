package com.example;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxTestUsingStepVerifier {
    @Test
    void testFlux1() {
        //create-Prepare a new StepVerifier in an uncontrolled environment:
        // Step.thenAwait will block in real time.
        // Each verify() will fully (re)play the scenario.
        //expectNext - Expect the next element received to be equal to the given value.
        //verfyComplete - Trigger the verification, expecting a completion signalas terminal event.

        Flux<String> flux = Flux.just("Spring MVC", "Spring Boot", "Spring Web");

        StepVerifier.create(flux.log())
                .expectNext("Spring MVC")
                .expectNext("Spring Boot")
                .expectNext("Spring Web")
                .verifyComplete();
    }

    @Test
    void testFlux2() {
        //expectNextCount-Expect to received count elements, starting from the previousexpectation or onSubscribe.
        Flux<String> flux = Flux.just("Spring MVC", "Spring Boot", "Spring Web");
        StepVerifier.create(flux.log())
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void testFlux3() {
        //expectError -Expect an error of the specified type.
        //verify -Verify the signals received by this subscriber.
        Flux<String> flux = Flux.just("Spring MVC", "Spring Boot", "Spring Web")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")));
        StepVerifier.create(flux.log())
                .expectNext("Spring MVC")
                .expectNext("Spring Boot")
                .expectNext("Spring Web")
                .expectError(RuntimeException.class)
                .verify();
    }
}

