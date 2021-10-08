package com.se.sample.company.service;

import com.se.sample.company.entity.Person;
import com.se.sample.company.repositories.PassportRepository;
import com.se.sample.company.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;


import static com.se.sample.company.repositories.specification.PassportSpecification.passportOwnedBy;
import static com.se.sample.company.repositories.specification.PassportSpecification.passportOwnerStartsWith;
import static com.se.sample.company.repositories.specification.PersonSpecification.personWorksIn;

import static com.se.sample.company.repositories.specification.PersonSpecification.personWorksIn;

public class PassportService {

    @Autowired
    PassportRepository passportRepository;
    @Autowired
    PersonRepository personRepository;

    public void test(){

        passportRepository.findAll(passportOwnedBy("Testoff"))
                .forEach(System.out::println);

        passportRepository.findAll(passportOwnerStartsWith("Te"))
                .forEach(System.out::println);

        personRepository.findAll(personWorksIn("Acme Ltd"))
                .forEach(System.out::println);

    }


}
