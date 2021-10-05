package com.se.product.service.domain.specification;

import com.se.product.service.domain.*;
import com.se.product.service.model.search.PagedProductSearchRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
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
                            .and(categoryCodeContains(request.getCategoryCode()))
                            .and(categoryNameContains(request.getCategoryName()))
                            .and(productCostBetweenBase(request.getCostFrom(), request.getCostTo()))
            )

                    .toPredicate(root, query, cb);
        };
    }

    private Specification<Product> productCostBetweenBase(String minValue, String maxValue) {
        // TODO: check how it works with String
        if (minValue != null && maxValue != null) {
            return searchByCostBetween(minValue, maxValue);
        } else if (minValue != null) {
            return searchByCostMin(minValue);
        } else if (maxValue != null) {
            searchByCostMax(maxValue);
        }

        return  null;
    }

    private Specification<Product> searchByCostBetween(String minValue, String maxValue) {
        return (root, query, cb) -> {

            Join<Product, Price> bs = root.join(Product_.PRICES);
            Path<String> stringPath = bs.get(Price_.COST);

            return  cb.between(cb.lower(stringPath), minValue, maxValue);
        };
    }

    private Specification<Product> searchByCostMin(String minValue) {
        return (root, query, cb) -> {
            Join<Product, Price> bs = root.join(Product_.PRICES);
            Path<String> stringPath = bs.get(Price_.COST);

            return  cb.greaterThanOrEqualTo(cb.lower(stringPath), minValue);
        };
    }

    private Specification<Product> searchByCostMax(String maxValue) {
        return (root, query, cb) -> {
            Join<Product, Price> bs = root.join(Product_.PRICES);
            Path<String> stringPath = bs.get(Price_.COST);

            return  cb.lessThanOrEqualTo(cb.lower(stringPath), maxValue);
        };
    }

    private Specification<Product> categoryCodeContains(String categoryCode) {

        if (categoryCode == null || StringUtils.isEmpty(categoryCode)) {
            return null;
        }

        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            // Join<ParentTable,ChildTable>
            Join<Product, Category> bs = root.join(Product_.CATEGORIES);
            Predicate predicate
                    = criteriaBuilder.like(bs.get(Category_.CODE), categoryCode);
            return predicate;
        };
    }


    private Specification<Product> categoryNameContains(String categoryName) {
        return (root, query, cb) -> {
            if (StringUtils.isEmpty(categoryName)) {
                return null;
            }
            Join<Product, Category> bs = root.join(Product_.CATEGORIES);
            Path<String> stringPath = bs.get(Category_.NAME);

            return cb.like(
                    cb.lower(stringPath),
                    containsLowerCase(categoryName));
        };
    }

    private Specification<Product> attributeContains(String attribute, String value) {
        return (root, query, cb) -> {
            if (value == null) {
                return null;
            }

            Path<String> expression = root.get(attribute);

            return cb.like(
                    cb.lower(expression),
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
