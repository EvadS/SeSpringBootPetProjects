package com.se.product.service.domain.specification;

import com.se.product.service.domain.Cat;
import com.se.product.service.domain.Owner;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.Collection;

public class Specs {
    public static Specification<Cat> hasOwnerName(final String ownerName) {
        return (root, query, cb) -> {
            query.distinct(true);
            Root<Cat> cat = root;
            Subquery<Owner> ownerSubQuery = query.subquery(Owner.class);
            Root<Owner> owner = ownerSubQuery.from(Owner.class);

            Expression<Collection<Cat>> ownerCats = owner.get("cats");
            ownerSubQuery.select(owner);
            ownerSubQuery.where(cb.equal(owner.get("name"), ownerName),
                    cb.isMember(cat, ownerCats));
            return cb.exists(ownerSubQuery);
        };
    }

    public static Specification<Cat> hasOwnerName2(final String ownerName) {
        return (root, query, cb) -> {
            query.distinct(true);
            Root<Cat> cat = root;
            Root<Owner> owner = query.from(Owner.class);
            Expression<Collection<Cat>> ownerCats = owner.get("cats");
            return cb.and(cb.equal(owner.get("name"), ownerName), cb.isMember(cat, ownerCats));
        };
    }
}
