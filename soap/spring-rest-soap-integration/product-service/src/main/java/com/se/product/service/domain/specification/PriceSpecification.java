package com.se.product.service.domain.specification;

import com.se.product.service.domain.Price;
import com.se.product.service.model.enums.CurrencyType;
import com.se.product.service.model.search.PriceSearch;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public final class PriceSpecification extends SearchSpecification<Price, PriceSearch> {

    private static String getContainsLikePattern(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return "%";
        } else {
            return "%" + searchTerm.toLowerCase() + "%";
        }
    }

    @Override
    public Specification<Price> getFilter(@Valid PriceSearch request) {
        return (root, query, cb) -> {
            query.distinct(true); //Important because of the join in the addressAttribute specifications


            return where(
                    (currencyTypeContains("currencyType", request.getCurrencyType()))
                            .and(lessThanOrEqualTo("cost", request.getPriceFrom())
                                    .or(lessThanOrEqualTo("cost", request.getPriceTo())))
            )
                    .toPredicate(root, query, cb);
        };
    }

    private Specification<Price> currencyTypeContains(String attribute, CurrencyType currencyType) {
        return (root, query, cb) -> {
            //TODO: How to move to list
//            if (transactionTypesList == null || transactionTypesList.isEmpty()) {
//                return null;
//            }

            return root.get(attribute).in(currencyType);
        };
    }

    private Specification<Price> lessThanOrEqualTo(String attribute, Double value) {
        if (value == null) {
            return null;
        }
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(attribute), value);
    }


    private Specification<Price> greaterThanOrEqualTo(String attribute, Double value) {
        if (value == null) {
            return null;
        }
        return (root, query, cb) ->
                cb.greaterThanOrEqualTo(root.get(attribute), value);
    }
}