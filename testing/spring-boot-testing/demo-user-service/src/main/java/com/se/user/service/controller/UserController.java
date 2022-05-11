package com.se.user.service.controller;

import com.se.user.service.model.User;
import com.se.user.service.service.AuthService;
import com.se.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping(path = "/uaa/v1")
public class UserController {


    private final UserService userService;

    private final AuthService authService;

    @Autowired
    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @RequestMapping(path = "/me")
    public ResponseEntity<User> me(Principal principal) throws Exception {
        return Optional.ofNullable(authService.getAuthenticatedUser(principal)) // <2>
                .map(p -> ResponseEntity.ok().body(userService.getUserByPrincipal(p))) // <3>
                .orElse(new ResponseEntity<User>(HttpStatus.UNAUTHORIZED)); // <4>
    }

}
