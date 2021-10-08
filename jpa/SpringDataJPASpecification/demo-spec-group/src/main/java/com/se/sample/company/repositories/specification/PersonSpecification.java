package com.se.sample.company.repositories.specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.se.sample.company.entity.Company;
import com.se.sample.company.entity.Person;
import org.springframework.data.jpa.domain.Specification;


public class PersonSpecification {
    public static Specification<Person> personWorksIn(final String companyName) {
        return new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                Join<Person, Company> company = root.join("workingPlaces");
                return criteriaBuilder.equal(company.get("name"), companyName);
            }
        };
    }

}