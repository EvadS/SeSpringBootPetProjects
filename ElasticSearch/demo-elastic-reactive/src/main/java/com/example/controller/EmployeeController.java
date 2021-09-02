package com.example.controller;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository repository;

    @PostMapping
    public Mono<Employee> add(@RequestBody Employee employee) {
        return repository.save(employee);
    }

    @GetMapping("/{name}")
    public Flux<Employee> findByName(@PathVariable("name") String name) {
        return repository.findByName(name);
    }

    @GetMapping
    public Flux<Employee> findAll() {
        return repository.findAll();
    }

    @GetMapping("/organization/{organizationName}")
    public Flux<Employee> findByOrganizationName(@PathVariable("organizationName") String organizationName) {
        return repository.findByOrganizationName(organizationName);
    }

}