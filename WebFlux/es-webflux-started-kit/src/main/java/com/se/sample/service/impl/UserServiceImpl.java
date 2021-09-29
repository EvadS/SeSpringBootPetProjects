package com.se.sample.service.impl;

import com.se.sample.entity.User;
import com.se.sample.models.UserDto;
import com.se.sample.models.request.UserRequest;
import com.se.sample.models.response.UserResponse;
import com.se.sample.repository.UserRepository;
import com.se.sample.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Log4j2
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<UserDto> getUser(String email) {
//        return userRepository.findUserByEmail(email)
//                .map(UserMapper.INSTANCE::toDTO)
//                .switchIfEmpty(Mono.error(UserNotFoundException::new));

        return Mono.empty();
    }

    @Override
    public Mono<UserResponse> createUser(UserRequest request) {
        return null;
    }

    @Override
    public Mono<UserResponse> getById(int id) {
        return null;
    }

    @Override
    public Mono<String> deleteById(int id) {
        return null;
    }

    @Override
    public Flux<User> findAll() {
        Flux<User> all = userRepository.findAll();
        return all;
    }
}
