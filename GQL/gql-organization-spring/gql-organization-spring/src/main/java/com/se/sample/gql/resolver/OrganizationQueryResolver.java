package com.se.sample.gql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.se.sample.gql.domain.Organization;
import com.se.sample.gql.repository.OrganizationRepository;
import org.springframework.stereotype.Component;

@Component
public class OrganizationQueryResolver implements GraphQLQueryResolver {

	private OrganizationRepository repository;

	OrganizationQueryResolver(OrganizationRepository repository) {
		this.repository = repository;
	}

	public Iterable<Organization> organizations() {
		return repository.findAll();
	}

	public Organization organization(Integer id) {
		return repository.findById(id).orElseThrow();
	}
}
