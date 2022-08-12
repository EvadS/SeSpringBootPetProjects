package com.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author Skiba Evgeniy
 * @date 10.09.2021
 */

@WebFluxTest(DemoController.class)
public class DemoControllerTest {
    @Autowired
    private WebTestClient webClient;

//    @MockBean
//    private UserVehicleService userVehicleService;

    @Test
    void testExample() throws Exception {
//        given(this.userVehicleService.getVehicleDetails("sboot"))
//                .willReturn(new VehicleDetails("Honda", "Civic"));
        this.webClient.get().uri("/demo/index").accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("hello");
    }
}
