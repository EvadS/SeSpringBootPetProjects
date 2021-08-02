package com.se.product.service.domain.specification;

import com.se.product.service.domain.Category;
import com.se.product.service.domain.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import java.util.BitSet;
import java.util.Date;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class ProductSpecification extends SearchSpecification<Product, ProductSearch> {

    @Override
    public Specification<Product> getFilter(ProductSearch request) {
        return (root, query, cb) -> {
            query.distinct(true); //Important because of the join in the addressAttribute specifications

        //    Specification<Transaction> walletFrom = walletFromAttributeEqual("walletFrom", request.getUserId(), request.getPaymentCurrency());
        //    Specification<Transaction> walletTo = walletFromAttributeEqual("walletTo", request.getUserId(), request.getPaymentCurrency());

            return where(
            //        dateGreaterThenSpec("createdAt", request.getDateFrom())
            //                .and(dateLessSpec("createdAt", request.getDateTo()))
          //                  .and((walletFrom).or(walletTo))

                    (attributeEqual("name", request.getName()))
                            .and(codeContains(request.getCategoryCode()))
                            .and(categoryNameContains(request.getCategoryName()))
            )
            .toPredicate(root, query, cb);
        };
    }



    private Specification<Product> attributeEqual(String attribute, String name) {
        return (root, query, cb) -> {
            if (name == null) {
                return null;
            }
            return cb.equal(root.get(attribute), name);
        };
    }

    private Specification<Product> codeContains(String code) {
        return categoryAttributeContains("code", code);
    }

    private Specification<Product> categoryNameContains(String categoryName) {
        return categoryAttributeContains("name", categoryName);
    }

    private Specification<Product> categoryAttributeContains(String attribute, String value) {
        return (root, query, cb) -> {
            if(value == null) {
                return null;
            }

            ListJoin<Product, Category> addresses = root.joinList("categories", JoinType.INNER);

            return cb.like(
                    cb.lower(addresses.get(attribute)),
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
