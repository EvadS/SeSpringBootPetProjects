package com.se.sample;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.stream.Collectors;

public class GenericSpecifications<T> {
    public Specification<T> groupBy(Specification<T> specification, List<String> columnNames) {
        return new Specification<T>() {

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                List<Expression<?>> columnNamesExpression = columnNames.stream().map(x -> root.get(x))
                        .collect(Collectors.toList());

                query.groupBy(columnNamesExpression);
                return specification.toPredicate(root, query, criteriaBuilder);
            }
        };
    }
}