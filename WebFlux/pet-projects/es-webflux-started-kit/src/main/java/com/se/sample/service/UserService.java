package com.se.sample.service;

import com.se.sample.entity.User;
import com.se.sample.models.UserDto;
import com.se.sample.models.request.UserRequest;
import com.se.sample.models.response.UserResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<UserDto> getUser(String email);

    Mono<UserResponse> createUser(UserRequest request);

    Mono<UserResponse> getById(int id);

    Mono<String> deleteById(int id);

    Flux<User> findAll();
}