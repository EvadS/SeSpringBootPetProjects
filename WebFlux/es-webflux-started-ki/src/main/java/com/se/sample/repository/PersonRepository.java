package com.se.sample.repository;


import com.se.sample.entity.model.Person;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends ReactiveElasticsearchRepository<Person, String> {
}