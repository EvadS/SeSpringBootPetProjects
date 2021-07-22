package com.se.product.service.mapper;


import com.se.product.service.domain.Price;
import com.se.product.service.model.PriceRequest;
import com.se.product.service.model.PriceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceMapper {

    PriceMapper MAPPER = Mappers.getMapper(PriceMapper.class);


    Price toPrice(PriceRequest priceRequest);

    PriceResponse toPriceResponse(Price price);
}
