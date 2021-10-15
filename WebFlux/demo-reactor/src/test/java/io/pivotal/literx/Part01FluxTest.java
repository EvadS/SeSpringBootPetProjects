package io.pivotal.literx;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class Part01FluxTest {

    Part01Flux workshop = new Part01Flux();

    @Test
    void emptyFlux() {
        Flux<String> flux = workshop.emptyFlux();

        StepVerifier.create(flux)
                .verifyComplete();
    }

    @Test
    void fooBarFluxFromValues() {
        Flux<String> flux = workshop.fooBarFluxFromValues();
        StepVerifier.create(flux)
                .expectNext("foo", "bar")
                .verifyComplete();
    }

    @Test
    void fooBarFluxFromList() {
        Flux<String> flux = workshop.fooBarFluxFromList();
        StepVerifier.create(flux)
                .expectNext("foo", "bar")
                .verifyComplete();
    }

    @Test
    void errorFlux() {
        Flux<String> flux = workshop.errorFlux();
        StepVerifier.create(flux)
                .verifyError(IllegalStateException.class);
    }

    @Test
    void counter() {
        Flux<Long> flux = workshop.counter();
        StepVerifier.create(flux)
                .expectNext(0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L)
                .verifyComplete();
    }
}