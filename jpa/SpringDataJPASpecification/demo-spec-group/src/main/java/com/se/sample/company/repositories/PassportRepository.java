package com.se.sample.company.repositories;

import com.se.sample.company.entity.Passport;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;


/**
 * Repository for passport entity.
 */
public interface PassportRepository extends CrudRepository<Passport, Long>, JpaSpecificationExecutor<Passport> { }
