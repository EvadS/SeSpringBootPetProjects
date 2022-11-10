package com.se.sample.gql.repository;

import com.se.sample.gql.domain.Organization;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationRepository extends CrudRepository<Organization, Integer> {
}