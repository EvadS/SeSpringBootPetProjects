package com.se.product.service.domain.specification;


import com.se.product.service.domain.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class SpecsProduct {


    /// 5,6,7

    // Combine predicate
    public static Specification<Product> hasOwnerName(final String name) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {

                Predicate predicateForBlueColor
                        = criteriaBuilder.equal(root.get("name"), "product");
                Predicate predicateForGradeA
                        = criteriaBuilder.equal(root.get("name"), name);

                Predicate finalPredicate
                        = criteriaBuilder
                        .or(predicateForBlueColor, predicateForGradeA);

                return finalPredicate;
            }
        };
    }

    // Работает
    // самый простой способ
    public static Specification<Product> testJoin( String name) {

        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                // Join<ParentTable,ChildTable>
                Join<Product, Category> bs = root.join(Product_.CATEGORIES);
                Predicate predicate
                        = criteriaBuilder.like(bs.get(Category_.NAME), name);
                return predicate;
            }
        };
    }




    public static Specification<Product> getProductById(final Long id) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Predicate predicateForId_3
                        = criteriaBuilder.equal(root.get("id"), id);

                return predicateForId_3;
            }
        };
    }

}
