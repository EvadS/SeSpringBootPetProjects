package com.se.sample.company.service;

import com.se.sample.company.entity.Person;
import com.se.sample.company.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public void test2(){
        Person queryPerson = new Person();
        queryPerson.setFirstName("Test");

        Example<Person> exampleQuery = Example.of(queryPerson);

        personRepository.findAll(exampleQuery)
                .forEach(System.out::println);


        ExampleMatcher firstNameMatcher = ExampleMatcher.matching()
                .withMatcher("firstname", m -> m.startsWith());
        queryPerson.setFirstName("fail");

        Example<Person> failingQuery = Example.of(queryPerson, firstNameMatcher);
        //  assertFalse(personRepository.exists(failingQuery));

        firstNameMatcher = ExampleMatcher.matching()
                .withMatcher("firstname", m -> m.startsWith());
        queryPerson.setFirstName("fail");
        failingQuery = Example.of(queryPerson, firstNameMatcher);
        // assertFalse(personRepository.exists(failingQuery));
    }
}
