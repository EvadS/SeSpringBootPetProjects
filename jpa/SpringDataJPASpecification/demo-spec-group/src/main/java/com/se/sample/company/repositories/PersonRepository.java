package com.se.sample.company.repositories;

import com.se.sample.company.entity.Person;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;


/**
 * Repository for person entity.
 */
public interface PersonRepository extends CrudRepository<Person, Long>,
        QueryByExampleExecutor<Person>,
        JpaSpecificationExecutor<Person> { }
