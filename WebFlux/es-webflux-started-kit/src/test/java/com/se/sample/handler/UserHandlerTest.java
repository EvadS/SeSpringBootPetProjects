package com.se.sample.handler;

import com.se.sample.components.UserRouter;
import com.se.sample.models.request.UserRequest;
import com.se.sample.models.response.UserResponse;
import com.se.sample.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UserRouter.class, UserHandler.class})
@WebFluxTest
public class UserHandlerTest {

    @Autowired
    private ApplicationContext context;

    @MockBean
    private UserService userService;

    private WebTestClient webTestClient;

    @Before
    public void setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    public void testGetUserById() {

        String email = "mail@mail.com";
        String fname = "fname";

        UserRequest userRequest = UserRequest.builder()
                .password("123456")
                .firstName(fname)
                .lastName("lastName")
                .phone("101")
                .email(email)
                .build();

        UserResponse userResponse = UserResponse.builder()
                .id(1l)
                .user(userRequest)
                .build();

        Mono<UserResponse> userResponseMono = Mono.just(userResponse);

        when(userService.getById(1)).thenReturn(userResponseMono);

        webTestClient.get()
                .uri("/users/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserResponse.class)
                .value(userResponse1 -> {
                            Assertions.assertThat(userResponse.getId()).isEqualTo(1);
                            Assertions.assertThat(userResponse.getUser().getFirstName()).isEqualTo(fname);
                            Assertions.assertThat(userResponse.getUser().getEmail()).isEqualTo(email);
                        }
                );
    }

//    @Test
//    public void testGetUsers() {
//
//        User user1 = User.builder().id(1).name("ABC").email("abc@xyz.com").build();
//        User user2 = User.builder().id(2).name("XYZ").email("xyz@abc.com").build();
//
//        when(userRepository.findAll()).thenReturn(Flux.just(user1, user2));
//
//        webTestClient.get()
//                .uri("/users")
//                .exchange()
//                .expectStatus().isOk()
//                .expectBodyList(User.class)
//                .value(userResponse -> {
//                            Assertions.assertThat(userResponse.get(0).getId()).isEqualTo(1);
//                            Assertions.assertThat(userResponse.get(0).getName()).isEqualTo("ABC");
//                            Assertions.assertThat(userResponse.get(0).getEmail()).isEqualTo("abc@xyz.com");
//                            Assertions.assertThat(userResponse.get(1).getId()).isEqualTo(2);
//                            Assertions.assertThat(userResponse.get(1).getName()).isEqualTo("XYZ");
//                            Assertions.assertThat(userResponse.get(1).getEmail()).isEqualTo("xyz@abc.com");
//                        }
//                );
//    }
//
//    @Test
//    public void testCreateUser() {
//
//        User user = User.builder().id(1).name("ABC").email("abc@xyz.com").build();
//        Mono<User> UserMono = Mono.just(user);
//        when(userRepository.save(any())).thenReturn(UserMono);
//
//        webTestClient.post()
//                .uri("/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .body(Mono.just(user), User.class)
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody(User.class)
//                .value(userResponse -> {
//                            Assertions.assertThat(userResponse.getId()).isEqualTo(1);
//                            Assertions.assertThat(userResponse.getName()).isEqualTo("ABC");
//                            Assertions.assertThat(userResponse.getEmail()).isEqualTo("abc@xyz.com");
//                        }
//                );
//    }
}