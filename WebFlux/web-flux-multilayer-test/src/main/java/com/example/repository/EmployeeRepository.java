package com.example.repository;


import com.example.domain.Employee;
import org.elasticsearch.client.security.user.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface EmployeeRepository extends ReactiveCrudRepository<Employee, String> {
    Flux<Employee> findByName(String name);
}