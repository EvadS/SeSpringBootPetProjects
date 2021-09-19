package com.example.service;

import com.example.dao.Employee;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;

/**
 * @author Skiba Evgeniy
 * @date 09.09.2021
 */
public interface EmployeeService {

     Mono<Page<Employee>> findAllUsersPaged();
}
