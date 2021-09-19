package com.se.sample.service;

import com.se.sample.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Skiba Evgeniy
 * @date 28.08.2021
 */
public interface EmployeeService {
    void create(Employee e);

    Mono<Employee> findById(Integer id);

    Flux<Employee> findByName(String name);

    Flux<Employee> findAll();

    Mono<Employee> update(Employee e);

    Mono<Void> delete(Integer id);
}
