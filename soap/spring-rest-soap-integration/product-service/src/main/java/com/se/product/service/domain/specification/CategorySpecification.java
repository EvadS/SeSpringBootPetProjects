package com.se.product.service.domain.specification;

import com.se.product.service.domain.Category;
import com.se.product.service.model.search.CategorySearch;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class CategorySpecification extends SearchSpecification<Category, CategorySearch> {

    @Override
    public Specification<Category> getFilter(CategorySearch request) {
        return (root, query, cb) -> {
            query.distinct(true);
            return where(
                    where(codeContains(request.getCode()))
                            .or(nameContains(request.getName()))
            ).toPredicate(root, query, cb);
        };
    }

    private Specification<Category> codeContains(String value) {
        return categoryContains("code", value);
    }

    private Specification<Category> nameContains(String value) {
        return categoryContains("name", value);
    }

    private Specification<Category> categoryContains(String attribute, String value) {
        return (root, query, cb) -> {
            if(value == null) {
                return null;
            }

            return cb.like(
                    cb.lower(root.get(attribute)),
                    containsLowerCase(value)
            );
        };
    }
}
