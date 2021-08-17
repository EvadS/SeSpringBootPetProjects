package com.se.product.service.domain.specification;

import com.se.product.service.domain.Category;
import com.se.product.service.domain.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.Collection;

public class CategorySpec {

    private CategorySpec() {
    }

    /**
     * get categories by product name
     *
     * @return
     */
    public static Specification<Category> categoryByProductName(String productName) {

        return (Specification<Category>) (root, query, cb) -> {

            Root<Category> categoryRoot = root;
            Subquery<Product> productSubQuery = query.subquery(Product.class);
            // we works on
            Root<Product> productRoot = productSubQuery.from(Product.class);

            Expression<Collection<Category>> productCategory = productRoot.get("categories");
            productSubQuery.select(productRoot);

            productSubQuery.where(cb.equal(productRoot.get("name"), productName),
                    cb.isMember(categoryRoot, productCategory));

            return cb.exists(productSubQuery);

        };
    }

}
