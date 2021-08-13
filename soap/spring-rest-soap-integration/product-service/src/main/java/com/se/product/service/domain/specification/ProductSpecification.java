package com.se.product.service.domain.specification;

import com.se.product.service.domain.Product;
import com.se.product.service.model.search.PagedProductSearchRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class ProductSpecification extends SearchSpecification<Product, PagedProductSearchRequest> {

    @Override
    public Specification<Product> getFilter(PagedProductSearchRequest request) {
        return (root, query, cb) -> {
            query.distinct(true); //Important because of the join in the addressAttribute specifications

            return where(

                    (attributeContains("name", request.getProductName()))
                    //   .and(codeContains(request.getCategoryCode()))
                    //  .and(categoryNameContains(request.getCategoryName()))
            )
                    .toPredicate(root, query, cb);
        };
    }




    private Specification<Product> codeContains(String code) {

        return attributeContains("code", code);
    }

    private Specification<Product> categoryNameContains(String categoryName) {
        return attributeContains("name", categoryName);
    }

    private Specification<Product> attributeContains(String attribute, String value) {
        return (root, query, cb) -> {
            if (value == null) {
                return null;
            }

            return cb.like(
                    cb.lower(root.get(attribute)),
                    containsLowerCase(value)
            );
        };
    }


    private Specification<Product> dateGreaterThenSpec(String attribute, long startedPeriod) {
        return dateGreaterThanOrEqualTo(attribute, startedPeriod);
    }

    private Specification<Product> dateLessSpec(String attribute, long endPeriod) {
        return dateLessThanOrEqualTo(attribute, endPeriod);
    }

    private Specification<Product> dateGreaterThanOrEqualTo(String attribute, long value) {
        return (root, query, cb) -> {
            if (value <= 0) {
                return null;
            }
            Date dateValue = new Date(value);

            return cb.greaterThanOrEqualTo(root.get(attribute), dateValue);
        };
    }

    private Specification<Product> dateLessThanOrEqualTo(String attribute, long value) {
        return (root, query, cb) -> {
            if (value <= 0) {
                return null;
            }

            Date dateValue = new Date(value);
            return cb.lessThanOrEqualTo(root.get(attribute), dateValue);
        };
    }

}
