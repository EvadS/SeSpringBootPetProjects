package com.se.example.controller;

import com.se.example.WebfluxTestingDemoApplication;
import com.se.example.dao.model.Employee;
import com.se.example.dao.repository.EmployeeRepository;
import com.se.example.dao.repository.impl.EmployeeRepositoryImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes= WebfluxTestingDemoApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeControllerIntegrationTest {

    @Autowired
    private WebTestClient testClient;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenCorrectEmployee() {

        Employee employee = new Employee("10000", "Employee TEST Name");

        given(employeeRepository.findEmployeeById("1")).willReturn(Mono.just(employee));

        testClient.get()
                .uri("/employees/1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Employee.class)
                .isEqualTo(employee);
    }

    @Test
    public void whenGetAllEmployees_thenCorrectEmployees() {

        List<Employee> employeeList = new ArrayList<>();

        Employee employee1 = new Employee("1000", "Employee 1 Name");
        Employee employee2 = new Employee("2000", "Employee 2 Name");
        Employee employee3 = new Employee("3000", "Employee 3 Name");

        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);

        Flux<Employee> employeeFlux = Flux.fromIterable(employeeList);

        given(employeeRepository.findAllEmployees()).willReturn(employeeFlux);
        testClient.get()
                .uri("/employees")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Employee.class)
                .hasSize(3)
                .isEqualTo(employeeList);
    }

}