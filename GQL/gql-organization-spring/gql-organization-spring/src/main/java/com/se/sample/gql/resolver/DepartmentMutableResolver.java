package com.se.sample.gql.resolver;


import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.se.sample.gql.domain.Department;
import com.se.sample.gql.domain.DepartmentInput;
import com.se.sample.gql.domain.Organization;
import com.se.sample.gql.repository.DepartmentRepository;
import com.se.sample.gql.repository.OrganizationRepository;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMutableResolver implements GraphQLMutationResolver {

	DepartmentRepository departmentRepository;
	OrganizationRepository organizationRepository;

	DepartmentMutableResolver(DepartmentRepository departmentRepository, OrganizationRepository organizationRepository) {
		this.departmentRepository = departmentRepository;
		this.organizationRepository = organizationRepository;
	}

	public Department newDepartment(DepartmentInput departmentInput) {
		Organization organization = organizationRepository.findById(departmentInput.getOrganizationId()).get();
		return departmentRepository.save(new Department(null, departmentInput.getName(), null, organization));
	}

}
