package com.se.sample.service;

import com.se.sample.mapper.UserMapper;
import com.se.sample.model.People;
import com.se.sample.model.UserDao;
import com.se.sample.model.UserResponse;
import com.se.sample.model.enums.Sex;
import com.se.sample.repository.UserRepo;
import lombok.Setter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Collection;

@Service
public class UserService {

    public  Flux<UserResponse>  getUsersInfo(){

        Flux<UserResponse> responseFlux = UserRepo.getUserList()
                .log()
                .map(i -> {
                    UserResponse response = UserMapper.toUserResponse(i);
                    return response;
                });
return responseFlux;
    }
}
