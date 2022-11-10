package com.se.sample.gql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.se.sample.gql.domain.Organization;
import com.se.sample.gql.domain.OrganizationInput;
import com.se.sample.gql.repository.OrganizationRepository;

import org.springframework.stereotype.Component;

@Component
public class OrganizationMutableResolver implements GraphQLMutationResolver {

	OrganizationRepository repository;

	OrganizationMutableResolver(OrganizationRepository repository) {
		this.repository = repository;
	}

	public Organization newOrganization(OrganizationInput organizationInput) {
		return repository.save(new Organization(null, organizationInput.getName(), null, null));
	}

}
