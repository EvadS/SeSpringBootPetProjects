package com.se.sample.repository;

import com.se.sample.logging.LogExecutionTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.se.sample.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

    @LogExecutionTime(textArgument = "1211212")
    List<Employee> findAll();
}
