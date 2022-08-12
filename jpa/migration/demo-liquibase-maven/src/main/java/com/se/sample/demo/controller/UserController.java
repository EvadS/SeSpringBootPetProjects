package com.se.sample.demo.controller;

import com.se.sample.demo.entities.User;
import com.se.sample.demo.model.UserRequest;
import com.se.sample.demo.model.response.UserResponse;
import com.se.sample.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Evgeniy Skiba on 01.04.21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<UserResponse>  createUser(@Valid  @RequestBody UserRequest userRequest){

        logger.debug("Handle new user request: {}", userRequest.getUserName());
        User user = new User(userRequest.getUserName(), userRequest.getAge());
        userRepository.save(user);

        UserResponse userResponse = new UserResponse(user.getId(), user.getUserName(), user.getAge());

        logger.debug("Created new user: {}", user.getId());
        return  ResponseEntity.ok(userResponse);
    }
}
