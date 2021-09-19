package com.example.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author WEb client testing
 * @date 10.09.2021
 */
@RunWith(SpringRunner.class)
@WebFluxTest(DemoController.class)
@ContextConfiguration(classes = {
        DemoController.class
})

public class SomeControllerTest {
    @Autowired
    private WebTestClient webClient;

    @Test
    public void deepMocking() {
        webClient.get()
                .uri("/test/index")
                .exchange()
                .expectStatus().isOk();

        int a = 0;
    }
    // ...
}