package com.se.sample.company.repositories.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.se.sample.company.entity.Passport;
import org.springframework.data.jpa.domain.Specification;




public class PassportSpecification {

    public static Specification<Passport> passportOwnedBy(final String lastName) {
        return new Specification<Passport>() {
            @Override
            public Predicate toPredicate(Root<Passport> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("owner").get("lastName"), lastName);
            }
        };
    }

    public static Specification<Passport> passportOwnerStartsWith(final String lastName) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("owner").get("lastName"), lastName+"%");
    }
}