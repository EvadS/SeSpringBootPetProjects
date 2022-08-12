package com.se.user.service.controller;

import static org.junit.Assert.*;


import com.se.user.service.model.User;
import com.se.user.service.service.AuthService;
import com.se.user.service.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.security.Principal;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthService authService;

    @Test
    public void getUserShouldReturnUser() throws Exception {

        Principal p1 = () -> "user";

        String content = "{\"username\": \"user\", \"firstName\": \"Jack\", \"lastName\": \"Frost\", \"email\": \"jfrost@example.com\"}";

        User user = new User("user", "Jack", "Frost", "jfrost@example.com");

        given(userService.getUserByPrincipal(p1)).willReturn(user);

        given(this.authService.getAuthenticatedUser(null)).willReturn(p1);

        this.mvc.perform(get("/uaa/v1/me").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json(content));
    }
}