package com.se.product.service.domain.specification;

import com.se.product.service.domain.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Collection;

public class CategorySpec {

    /**
     * если мы хотим взять все категории по продукт-нейм

     * @return
     */
    public static Specification<Category> categoryByProductName(String productName) {

        return new Specification<Category>() {
            @Override
            public Predicate toPredicate(Root<Category> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {

                Root<Category> categoryRoot = root;
                Subquery<Product> productSubQuery = query.subquery(Product.class);
                // над чем работаем
                Root<Product> productRoot = productSubQuery.from(Product.class);

                Expression<Collection<Category>> productCategory = productRoot.get("categories");
                productSubQuery.select(productRoot);

                productSubQuery.where(cb.equal(productRoot.get("name"), productName),
                        cb.isMember(categoryRoot, productCategory));

                return cb.exists(productSubQuery);

            }
        };
    }

}
