package com.se.sample.handler;

import com.se.sample.entity.User;
import com.se.sample.models.request.UserRequest;
import com.se.sample.models.response.UserResponse;
import com.se.sample.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@AllArgsConstructor
public class UserHandler {

    private UserService userService;

    public Mono<ServerResponse> createUser(ServerRequest request) {
        Mono<UserRequest> user = request.bodyToMono(UserRequest.class);
        return ok().contentType(APPLICATION_JSON)
                .body(fromPublisher(user.flatMap(userService::createUser), UserResponse.class));
    }

    public Mono<ServerResponse> getUser(ServerRequest request) {
        final int id = Integer.parseInt(request.pathVariable("id"));
        final Mono<UserResponse> user = userService.getById(id);
        return user.flatMap(usr -> ok().contentType(APPLICATION_JSON)
                .body(fromPublisher(user, UserResponse.class)))
                .switchIfEmpty(notFound().build())
                ;
    }

    public Mono<ServerResponse> getUsers(ServerRequest request) {
        return ok().contentType(APPLICATION_JSON)
                .body(fromPublisher(userService.findAll(), User.class));
    }
}