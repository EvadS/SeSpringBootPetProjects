package com.se.sample.handlers;

import com.se.sample.model.Message;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class GreetingHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {

        Long start = request.queryParam("start")
                        .map(Long::valueOf)
                        .orElse(0l);

        Long count = request.queryParam("count")
                .map(Long::valueOf)
                .orElse(3l);

        String [] arr = {};
        //?sort=numberOfHands,desc&name,asc
        String  sort = request.queryParam("sort")
                //.map(Long::valueOf)
                .orElse("");


        Flux<Message> data = Flux
                .just("Hello Reactive!",
                        "More than one",
                        "Third post",
                        "Fourth post",
                        "Fifth post"

                )
                .skip(start)
                .take(count)
                .map(Message::new);

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(data, Message.class);
    }

    public Mono<ServerResponse> index(ServerRequest serverRequest) {

        String user = serverRequest.queryParam("user").orElse("Nobody");

        return ServerResponse
                .ok()
                .render("index", Map.of("user", user));

    }
}