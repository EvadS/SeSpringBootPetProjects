package com.sample.rest.server.service;

import com.sample.rest.server.repository.EmployeeRepository;
import com.sample.rest.server.model.domain.EmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeVO getEmployeeById(Long id) {
        return employeeRepository.getByEmployeeId(id);
    }

    public EmployeeVO addEmployee(EmployeeVO employee) {
        EmployeeVO employeeVO =  employeeRepository.save(employee);
        return  employeeVO;
    }
}
