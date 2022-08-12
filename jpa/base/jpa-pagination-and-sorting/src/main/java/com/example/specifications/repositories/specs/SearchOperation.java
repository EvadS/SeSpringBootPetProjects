package com.example.specifications.repositories.specs;

/**
 * @author Evgeniy Skiba on 20.06.2020
 * @project spring-data-jpa
 */
public enum SearchOperation {
    GREATER_THAN,
    LESS_THAN,
    GREATER_THAN_EQUAL,
    LESS_THAN_EQUAL,
    NOT_EQUAL,
    EQUAL,
    MATCH,
    MATCH_START,
    MATCH_END,
    IN,
    NOT_IN
}