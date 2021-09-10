package com.example.controller;

import com.example.model.Employee;
import com.example.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testng.annotations.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Skiba Evgeniy
 * @date 10.09.2021
 */

@AllArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

    private final EmployeeService employeeService;

    @GetMapping("/index")
    public Flux<String> paged() {
        return Flux.just("hello");
    }
}
