package io.pivotal.literx;


import io.pivotal.literx.domain.User;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Part07Errors {

    //========================================================================================
    // TODO Return a Mono<User> containing User.SAUL when an error occurs in the input Mono, else do not change the input Mono.
    Mono<User> betterCallSaulForBogusMono(Mono<User> mono) {
      //  when an error occurs in the original Mono, using the method onErrorReturn
        ///иначе вернуть то чтона вход
        return mono.onErrorReturn(User.SAUL);
    }

    //========================================================================================
    // TODO Return a Flux<User> containing User.SAUL and User.JESSE when an error occurs in the input Flux, else do not change the input Flux.
    Flux<User> betterCallSaulAndJesseForBogusFlux(Flux<User> flux) {
        return flux.onErrorResume(
                e -> Flux.just(User.SAUL, User.JESSE));
    }

    //========================================================================================
    // TODO Implement a method that capitalizes each user of the incoming flux using the
    // #capitalizeUser method and emits an error containing a GetOutOfHereException error
    Flux<User> capitalizeMany(Flux<User> flux) {


        return flux.map(
                u -> {
                    try {
                        return capitalizeUser(u);
                    } catch (GetOutOfHereException e) {
                        throw Exceptions.propagate(e);
                    }
                }
        );
    }

    User capitalizeUser(User user) throws GetOutOfHereException {
        if (user.equals(User.SAUL)) {
            throw new GetOutOfHereException();
        }
        return new User(user.getUsername(), user.getFirstname(), user.getLastname());
    }

    protected final class GetOutOfHereException extends Exception {
        private static final long serialVersionUID = 0L;
    }

}
