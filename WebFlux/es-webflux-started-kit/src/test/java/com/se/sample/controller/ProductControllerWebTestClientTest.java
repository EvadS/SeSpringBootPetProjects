package com.se.sample.controller;


import com.se.sample.repository.ProductRepository;
import com.se.sample.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductControllerWebTestClientTest {

    // TODO: repo should be mocked
    @Autowired
    private ApplicationContext context;
    private WebTestClient client;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        client = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    public void defaultPaginationTest() {
        WebTestClient.BodyContentSpec equalTo = client.get()
                .uri("/products/paged")
                .exchange()
                .expectBody()
                .jsonPath("$.content").isArray()
                .jsonPath("$.content.length()").isEqualTo(1)
                .jsonPath("pageNumber").isEqualTo(0)
                .jsonPath("pageSize").isEqualTo(20)
                .jsonPath("totalElements").isEqualTo(1)
                .jsonPath("first").isEqualTo(true)
                .jsonPath("last").isEqualTo(true)
          //      .jsonPath("totalPages").isEqualTo(4)
              ;

    }

    @Test
    public void requestedPaginationTest() {
        client.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/products/paged")
                                .queryParam("page", 1)
                                .queryParam("size", 2)
                                .build())
                .exchange()
                .expectBody()
                .jsonPath("$.content").isArray()
                .jsonPath("$.content.length()").isEqualTo(0)
                .jsonPath("pageNumber").isEqualTo(1)
                .jsonPath("pageSize").isEqualTo(2)
                .jsonPath("totalElements").isEqualTo(1)
//                .jsonPath("first").isEqualTo(false)
//                .jsonPath("last").isEqualTo(false)
 //               .jsonPath("totalPages").isEqualTo(4)
                      ;
    }
}
