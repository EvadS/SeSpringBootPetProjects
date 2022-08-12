package com.se.specification.example.controller.specification;

/**
 * @author Evgeniy Skiba on 20.06.2020
 * @project spring-data-jpa
 */
import org.springframework.data.jpa.domain.Specification;

public abstract class BaseSpecification<T, U> {

    private final String wildcard = "%";

    public abstract Specification<T> getFilter(U request);

    protected String containsLowerCase(String searchField) {
        return wildcard + searchField.toLowerCase() + wildcard;
    }
}