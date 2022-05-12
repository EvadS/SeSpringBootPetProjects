package com.se.example.dao.repository;


import com.se.example.dao.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeRepository {

    Mono<Employee> findEmployeeById(String id);

    Flux<Employee> findAllEmployees();

    Mono<Employee> updateEmployee(Employee employee);
}
