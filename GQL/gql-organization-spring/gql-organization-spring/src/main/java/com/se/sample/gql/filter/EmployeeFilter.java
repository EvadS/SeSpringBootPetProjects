package com.se.sample.gql.filter;

import lombok.Data;

/**
 *  implementation is EmployeeFilter class that has three possible
 *  criteria of filtering by salary, age and position.
 */
@Data
public class EmployeeFilter {
    private FilterField salary;
    private FilterField age;
    private FilterField position;
}