package com.se.sample.service;

import com.se.sample.models.UserDto;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<UserDto> getUser(String email);
}