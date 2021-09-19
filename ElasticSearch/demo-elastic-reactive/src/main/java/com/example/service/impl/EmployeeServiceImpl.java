package com.example.service.impl;

import com.example.dao.Employee;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

/**
 * @author Skiba Evgeniy
 * @date 09.09.2021
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Mono<Page<Employee>> findAllUsersPaged() {

        Pageable pageable = PageRequest.of(2, 5);

        return this.employeeRepository.count()
                .flatMap(userCount -> {
                    return this.employeeRepository.findAll(pageable.getSort())
                            .buffer(pageable.getPageSize(), (pageable.getPageNumber() + 1))
                            .elementAt(pageable.getPageNumber(), new ArrayList<>())
                            .map(users -> new PageImpl<Employee>(users, pageable, userCount));
                });
    }
}
