package com.example.controller;

import com.example.dao.Employee;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository repository;
    private final EmployeeService employeeService;

    @PostMapping
    public Mono<Employee> add( @Valid @RequestBody Employee employee) {
        log.info("create new Employee, data:{}", employee);
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

    @GetMapping("/all")
    public Mono<Page<Employee>> paged() {
        log.info("Get paged");
        return employeeService.findAllUsersPaged();
    }

}