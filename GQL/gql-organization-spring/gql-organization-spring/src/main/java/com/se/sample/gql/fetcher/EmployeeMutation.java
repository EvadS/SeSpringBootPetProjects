package com.se.sample.gql.fetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.se.sample.gql.domain.Department;
import com.se.sample.gql.domain.Employee;
import com.se.sample.gql.domain.EmployeeInput;
import com.se.sample.gql.domain.Organization;
import com.se.sample.gql.repository.DepartmentRepository;
import com.se.sample.gql.repository.EmployeeRepository;
import com.se.sample.gql.repository.OrganizationRepository;

@DgsComponent
public class EmployeeMutation {

    DepartmentRepository departmentRepository;
    EmployeeRepository employeeRepository;
    OrganizationRepository organizationRepository;

    EmployeeMutation(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, OrganizationRepository organizationRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.organizationRepository = organizationRepository;
    }

    @DgsData(parentType = "MutationResolver", field = "newEmployee")
    public Employee addEmployee(@InputArgument("input") EmployeeInput employeeInput) {
        Department department = departmentRepository.findById(employeeInput.getDepartmentId()).orElseThrow();
        Organization organization = organizationRepository.findById(employeeInput.getOrganizationId()).orElseThrow();
        return employeeRepository.save(new Employee(null, employeeInput.getFirstName(), employeeInput.getLastName(),
                employeeInput.getPosition(), employeeInput.getAge(), employeeInput.getSalary(),
                department, organization));
    }

}
