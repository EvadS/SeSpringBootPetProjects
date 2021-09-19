package com.se.sample.repository;

import com.se.sample.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Skiba Evgeniy
 * @date 28.08.2021
 */

public class EmployeeRepository {


    public Mono<Employee> findById(Integer id) {

        return Mono.empty();
    }

    public Flux<Employee> findByName(final String name) {
        Employee employee = new Employee();

        return Flux.just(employee);
    }

    public Flux<Employee> findAll() {
        return Flux.empty();
    }

    public Mono<Void> deleteById(Integer id) {
        return Mono.empty();
    }

    public Mono<Employee> save(Employee e) {
        return Mono.empty();
    }
}
