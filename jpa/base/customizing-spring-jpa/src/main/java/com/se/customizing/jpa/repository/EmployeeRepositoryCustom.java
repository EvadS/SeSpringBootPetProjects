package com.se.customizing.jpa.repository;

import com.se.customizing.jpa.entity.Employee;

import java.util.List;

public interface EmployeeRepositoryCustom {
    List<Employee> getFirstNamesLike(String firstName);
}
