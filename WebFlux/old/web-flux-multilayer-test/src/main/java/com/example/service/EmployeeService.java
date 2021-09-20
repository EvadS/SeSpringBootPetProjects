package com.example.service;

import com.example.domain.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Skiba Evgeniy
 * @date 10.09.2021
 */
public interface EmployeeService
{
    void create(Employee e);

    Mono<Employee> findById(String id);

    Flux<Employee> findByName(String name);

    Flux<Employee> findAll();

    Mono<Employee> update(Employee e);

    Mono<Void> delete(String id);
}