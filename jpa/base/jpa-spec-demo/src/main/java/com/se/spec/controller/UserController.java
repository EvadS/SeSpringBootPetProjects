package com.se.spec.controller;


import com.se.spec.domain.User;
import com.se.spec.dto.UserDto;
import com.se.spec.dto.UserListRequest;
import com.se.spec.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController  {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            value = "",
            method = RequestMethod.GET)
    public  Page<UserDto>getAllUser(UserListRequest request, Pageable pageable) {
        return userService.findAll(request, pageable);
    }
}
