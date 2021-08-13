package com.se.product.service.mapper;


import com.se.product.service.domain.Price;
import com.se.product.service.model.enums.CurrencyType;
import com.se.product.service.model.payload.PriceSearchModel;
import com.se.product.service.model.request.PriceRequest;
import com.se.product.service.model.response.PriceResponse;
import com.se.product.service.model.search.PriceSearchRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Mapper
public interface PriceMapper {

    PriceMapper MAPPER = Mappers.getMapper(PriceMapper.class);

    @Named("currenciesToIdsList")
    static List<Integer> currenciesToIdsList(String currencyTypeName) {

        if (!StringUtils.hasLength(currencyTypeName))
            return Collections.EMPTY_LIST;

        try {
            return Arrays.asList(
                    CurrencyType.valueOf(currencyTypeName).getId()
            );
        } catch (Exception ex) {
            // incorrect value
            return Collections.EMPTY_LIST;
        }
    }

    @Mappings({
            @Mapping(target = "currencyType", source = "currencyType"),
            @Mapping(target = "cost", source = "cost")
    })
    Price toPrice(PriceRequest priceRequest);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "price.cost", source = "cost"),
            @Mapping(target = "price.currencyType", source = "currencyType")
    })
    PriceResponse toPriceResponse(Price price);

    @Mappings({
            @Mapping(target = "costFrom", source = "priceFrom"),
            @Mapping(target = "costTo", source = "priceTo"),
            @Mapping(source = "currencyType", target = "currenciesList", qualifiedByName = "currenciesToIdsList"),
    })
    PriceSearchModel toPriceSearchModel(PriceSearchRequest priceSearchRequest);
}
