package com.se.sample.gql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.se.sample.gql.domain.Department;
import com.se.sample.gql.domain.Employee;
import com.se.sample.gql.domain.EmployeeInput;
import com.se.sample.gql.domain.Organization;
import com.se.sample.gql.repository.DepartmentRepository;
import com.se.sample.gql.repository.EmployeeRepository;
import com.se.sample.gql.repository.OrganizationRepository;
import org.springframework.stereotype.Component;


@Component
public class EmployeeMutableResolver implements GraphQLMutationResolver {


	DepartmentRepository departmentRepository;
	EmployeeRepository employeeRepository;
	OrganizationRepository organizationRepository;

	EmployeeMutableResolver(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, OrganizationRepository organizationRepository) {
		this.departmentRepository = departmentRepository;
		this.employeeRepository = employeeRepository;
		this.organizationRepository = organizationRepository;
	}

	public Employee newEmployee(EmployeeInput employeeInput) {
		Department department = departmentRepository.findById(employeeInput.getDepartmentId()).get();
		Organization organization = organizationRepository.findById(employeeInput.getOrganizationId()).get();
		return employeeRepository.save(new Employee(null, employeeInput.getFirstName(), employeeInput.getLastName(),
				employeeInput.getPosition(), employeeInput.getAge(), employeeInput.getSalary(),
				department, organization));
	}

}
