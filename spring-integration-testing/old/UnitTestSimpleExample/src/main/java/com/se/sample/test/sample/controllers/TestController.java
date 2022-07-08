package com.se.sample.test.sample.controllers;


import com.se.sample.test.sample.model.Employee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TestController {

    @RequestMapping(value = "/employee", method = RequestMethod.GET, produces = "application/json")
    public Employee firstPage() {

        Employee emp = new Employee();
        emp.setName("emp1");
        emp.setDesignation("manager");
        emp.setEmpId("1");
        emp.setSalary(3000);

        return emp;
    }

}