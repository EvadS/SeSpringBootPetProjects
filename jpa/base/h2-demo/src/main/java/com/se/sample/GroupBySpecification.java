package com.se.sample;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Parameters;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class GroupBySpecification{// implements Specification {
//    Specification original;
//    Parameters parameters; // whatever data needed for calculating groupBy expression
//
//    public GroupBySpecification(Specification original, Parameters parameters) {
//        this.original = original;
//        this.parameters = parameters;
//    }
//
//    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//
//        Expression<?> expression = ...; // whatever logic needed to be used on parameters...
//        criteriaBuilder.groupBy(expression);
//        return original.toPredicate(root, query, criteriabuilder);
//    }

}