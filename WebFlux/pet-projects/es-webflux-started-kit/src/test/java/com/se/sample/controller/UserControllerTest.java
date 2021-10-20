package com.se.sample.controller;

import com.se.sample.models.request.UserRequest;
import com.se.sample.models.response.UserResponse;
import com.se.sample.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebFluxTest(UserController.class)
public class UserControllerTest {
    @Autowired
    WebTestClient webTestClient;

    @MockBean
    private UserService userService;

    @Test
    public void testGetById() {

        String email = "mail@mail.com";
        UserRequest userRequest = UserRequest.builder()
                .password("123456")
                .firstName("fname")
                .lastName("lastName")
                .phone("101")
                .email(email)
                .build();

        UserResponse userResponse = UserResponse.builder()
                .id(1l)
                .user(userRequest)
                .build();

        Mono<UserResponse> employeeMono = Mono.just(userResponse);

        when(userService.getById(1)).thenReturn(employeeMono);

        webTestClient.get()
                .uri("/users/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserResponse.class)
                .value(usr -> userResponse.getUser().getEmail(), equalTo(email));
    }

    @Test
    public void testDeleteById() {

        when(userService.deleteById(1)).thenReturn(Mono.just("User with id 1 is deleted."));

        // non-blocking, reactive client for testing web servers.
        webTestClient.delete()
                .uri("/users/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("user with id 1 is deleted.");
    }

    @Test
    public void testCreateEmployee() {

        String email = "mail@mail.com";
        UserRequest userRequest = UserRequest.builder()
                .password("123456")
                .firstName("fname")
                .lastName("lastName")
                .phone("101")
                .email(email)
                .build();

        UserResponse userResponse = UserResponse.builder()
                .id(1l)
                .user(userRequest)
                .build();

        Mono<UserResponse> userMono = Mono.just(userResponse);
        when(userService.createUser(userRequest).thenReturn(userMono));

        webTestClient.post()
                .uri("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(userResponse), UserResponse.class)
                .exchange()
                .expectStatus().isCreated();
    }
}