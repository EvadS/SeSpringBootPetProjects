package io.pivotal.literx;



import java.time.Duration;
import java.util.function.Supplier;

import io.pivotal.literx.domain.User;
import org.assertj.core.api.Assertions;
import reactor.core.publisher.Flux;

import reactor.test.StepVerifier;

/**
 * Learn how to use StepVerifier to test Mono, Flux or any other kind of Reactive Streams Publisher.
 *
 * @author Sebastien Deleuze
 * @see <a href="https://projectreactor.io/docs/test/release/api/reactor/test/StepVerifier.html">StepVerifier Javadoc</a>
 */
public class Part03StepVerifier {

//========================================================================================

    // TODO Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then completes successfully.
    void expectFooBarComplete(Flux<String> flux) {
     //   Flux<String> strFlux = Flux.fromArray(new String []{"foo","bar"});
        StepVerifier.create(flux)
                .expectNext("foo")
                .expectNext("bar")
                .expectComplete();

    }

//========================================================================================

    // TODO Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then a RuntimeException error.
    void expectFooBarError(Flux<String> flux) {
        StepVerifier.create(flux)
                .expectNext("foo", "bar")
                .verifyError(RuntimeException.class);
    }

//========================================================================================

    // TODO Use StepVerifier to check that the flux parameter emits a User with "swhite"username
    // and another one with "jpinkman" then completes successfully.
    void expectSkylerJesseComplete(Flux<User> flux) {
        StepVerifier.create(flux)
                .assertNext(t -> Assertions.assertThat(t.getUsername()).isEqualTo("swhite"));
    }

//========================================================================================

    // TODO Expect 10 elements then complete and notice how long the test takes.
    void expect10Elements(Flux<Long> flux) {
        StepVerifier.create(flux).expectNextCount(10).verifyComplete();
    }

//========================================================================================

    // TODO Expect 3600 elements at intervals of 1 second, and verify quicker than 3600s
    // by manipulating virtual time thanks to StepVerifier#withVirtualTime, notice how long the test takes
    void expect3600Elements(Supplier<Flux<Long>> supplier) {

        StepVerifier.withVirtualTime(supplier).expectSubscription()
                .expectNoEvent(Duration.ofSeconds(1))
                .thenAwait(Duration.ofHours(1))
                .expectNextCount(10)
             //   .expectComplete()
     //           .verify();

    ;
    }

    private void fail() {
        throw new AssertionError("workshop not implemented");
    }

}
