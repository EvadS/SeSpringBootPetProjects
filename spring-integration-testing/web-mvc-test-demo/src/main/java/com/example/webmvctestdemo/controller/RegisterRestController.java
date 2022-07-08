package com.example.webmvctestdemo.controller;

import com.example.webmvctestdemo.domain.RegisterUseCase;
import com.example.webmvctestdemo.domain.User;
import com.example.webmvctestdemo.web.UserResource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RegisterRestController {

    private final RegisterUseCase registerUseCase;

    @PostMapping("/forums/{forumId}/register")
    UserResource register(
            @PathVariable("forumId") Long forumId,
            @Valid @RequestBody UserResource userResource,
            @RequestParam("sendWelcomeMail") boolean sendWelcomeMail) {

        User user = new User(
                userResource.getName(),
                userResource.getEmail());
        Long userId = registerUseCase.registerUser(user, sendWelcomeMail);

        return new UserResource(
                user.getName(),
                user.getEmail());
    }
}
