package com.se.product.service.domain.specification;

import com.se.product.service.domain.User;
import com.se.product.service.domain.User_;
import org.springframework.data.jpa.domain.Specification;


public final class UserFirstnameSpecification {

    private UserFirstnameSpecification() {
    }


    public static Specification<User> firstOrLastNameContainsIgnoreCase(String searchTerm) {
        return (root, query, cb) -> {
            String containsLikePattern = getContainsLikePattern(searchTerm);
            return cb.or(
                    cb.like(cb.lower(root.get(User_.firstName)), containsLikePattern),
                    cb.like(cb.lower(root.get(User_.username)), containsLikePattern),
                    cb.like(cb.lower(root.get(User_.lastName)), containsLikePattern)
            );
        };
    }

    private static String getContainsLikePattern(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return "%";
        } else {
            return "%" + searchTerm.toLowerCase() + "%";
        }
    }
}
