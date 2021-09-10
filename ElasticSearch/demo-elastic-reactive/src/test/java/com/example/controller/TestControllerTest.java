package com.example.controller;

import com.example.service.EmployeeService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;


import static org.junit.jupiter.api.Assertions.*;

/**
 * @author * @author WEb client testing
 * @date 10.09.2021
 */


@RunWith(SpringRunner.class)
@WebFluxTest(TestController.class)
@ContextConfiguration(classes = {
        TestController.class,
})
public class TestControllerTest {

    @Autowired
    private TestController controller;

    @Test
    public void test(){
        int a =0;

        WebTestClient.bindToController(controller)
                .build()
                .get()
                .uri("/test/index")
                .exchange()
                .expectStatus().isOk();

        int bb=0;
    }
}