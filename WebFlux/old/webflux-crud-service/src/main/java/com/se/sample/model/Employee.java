package com.se.sample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Skiba Evgeniy
 * @date 28.08.2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    int id;
    String name;
    long salary;
}
