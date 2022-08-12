package com.spring.cloud.repository;

import com.spring.cloud.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}