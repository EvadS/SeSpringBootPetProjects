package com.example.controller;


import java.util.ArrayList;
import java.util.List;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Skiba Evgeniy
 * @date 10.09.2021
 */

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = EmployeeController.class)

@ContextConfiguration(classes = {
        EmployeeService.class
})
public class EmployeeControllerTest {

    @MockBean
    EmployeeRepository repository;

    @Autowired
    private WebTestClient webClient;

    @Test
    void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Test");
        employee.setSalary(1000);

        Mockito.when(repository.save(employee)).thenReturn(Mono.just(employee));

        webClient.post()
                .uri("/create")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(employee))
                .exchange()
                .expectStatus().isCreated();

        Mockito.verify(repository, Mockito.times(1)).save(employee);
    }

    @Test
    void testGetEmployeeById()
    {
        Employee employee = new Employee();
        employee.setId(100);
        employee.setName("Test");
        employee.setSalary(1000);

        Mockito
                .when(repository.findById("100"))
                .thenReturn(Mono.just(employee));

        webClient.get().uri("/{id}", 100)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isNotEmpty()
                .jsonPath("$.id").isEqualTo(100)
                .jsonPath("$.name").isEqualTo("Test")
                .jsonPath("$.salary").isEqualTo(1000);

    }
}