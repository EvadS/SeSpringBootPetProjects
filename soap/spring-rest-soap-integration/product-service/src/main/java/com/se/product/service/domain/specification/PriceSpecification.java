package com.se.product.service.domain.specification;

import com.se.product.service.domain.Price;
import com.se.product.service.model.payload.PriceSearchModel;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public final class PriceSpecification extends SearchSpecification<Price, PriceSearchModel> {

    @Override
    public Specification<Price> getFilter(@Valid PriceSearchModel request) {
        return (root, query, cb) -> {
            query.distinct(true);
            return where(
                    (currencyTypeContains("currencyType", request.getCurrenciesList()))
                            .and(betweenAttributes("cost", request.getCostFrom(), request.getCostTo())))
                    .toPredicate(root, query, cb);
        };
    }


    private Specification<Price> currencyTypeContains(String attribute, List<Integer> currencyTypeIds) {
        return (root, query, cb) -> {
            if (CollectionUtils.isEmpty(currencyTypeIds)) {
                return null;

            }

            return root.get(attribute).in(currencyTypeIds);
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

    private Specification<Price> betweenAttributes(String attribute, Double minValue, Double maxValue) {

        if (minValue != null && maxValue != null) {
            return (root, query, cb) -> cb.between(root.get(attribute), minValue, maxValue);
        } else if (minValue != null) {
            return greaterThanOrEqualTo("cost", minValue);
        } else if (maxValue != null) {
            return lessThanOrEqualTo("cost", maxValue);
        }

        return null;
    }
}