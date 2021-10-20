package io.pivotal.literx;

import java.util.*;
import java.util.function.*;
import java.time.*;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Part04Transform {

//========================================================================================

    // TODO Capitalize the user username, firstname and lastname
    Mono<User> capitalizeOne(Mono<User> mono) {
        return mono.map(t-> new User(
                t.getUsername().toUpperCase(),
                t.getFirstname().toUpperCase(),
                t.getLastname().toUpperCase()
        ));
    }

//========================================================================================

    // TODO Capitalize the users username, firstName and lastName
    Flux<User> capitalizeMany(Flux<User> flux) {
        return flux.map( t -> new User(
                t.getUsername().toUpperCase(),
                t.getFirstname().toUpperCase(),
                t.getLastname().toUpperCase()));
    }

//========================================================================================

    // TODO Capitalize the users username, firstName and lastName using #asyncCapitalizeUser
    Flux<User> asyncCapitalizeMany(Flux<User> flux) {
        return flux.flatMap( this::asyncCapitalizeUser);
    }

    Mono<User> asyncCapitalizeUser(User u) {
        return Mono.just(
                new User(u.getUsername().toUpperCase(),
                        u.getFirstname().toUpperCase(),
                        u.getLastname().toUpperCase())
        );
    }

}
