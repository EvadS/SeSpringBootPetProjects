package com.se.sample.gql.fetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.se.sample.gql.domain.Organization;
import com.se.sample.gql.domain.OrganizationInput;
import com.se.sample.gql.repository.OrganizationRepository;


@DgsComponent
public class OrganizationMutation {

    OrganizationRepository repository;

    OrganizationMutation(OrganizationRepository repository) {
        this.repository = repository;
    }

    @DgsData(parentType = "MutationResolver", field = "newOrganization")
    public Organization newOrganization(OrganizationInput organizationInput) {
        return repository.save(new Organization(null, organizationInput.getName(), null, null));
    }
}