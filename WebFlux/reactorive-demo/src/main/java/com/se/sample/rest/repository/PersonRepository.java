package com.se.sample.rest.repository;


import com.se.sample.rest.entity.Person;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends ReactiveSortingRepository<Person, String> {
}
