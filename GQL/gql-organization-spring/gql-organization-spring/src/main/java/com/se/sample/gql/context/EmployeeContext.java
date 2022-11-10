package com.se.sample.gql.context;

import com.se.sample.gql.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class EmployeeContext {
    private List<Employee> employees = new ArrayList<>();
}