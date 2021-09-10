package com.example.controller;


import com.example.service.EmployeeService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.junit.Test;

/**
 * @author WEb client testing
 * @date 10.09.2021
 */
@RunWith(SpringRunner.class)
@WebFluxTest(TestController.class)
@ContextConfiguration(classes = {
        TestController.class
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