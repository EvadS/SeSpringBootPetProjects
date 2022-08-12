package com.se.product.service.service;

import com.se.product.service.model.request.PriceRequest;
import com.se.product.service.model.response.PriceResponse;
import com.se.product.service.model.search.PriceSearchRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PriceService {
    PriceResponse create(PriceRequest request);

    PriceResponse updatePrice(Long priceId, PriceRequest requestModel);

    void deletePrice(Long id);

    PriceResponse getById(Long priceId);

    List<PriceResponse> getAll();

    Page<PriceResponse> getPaged(PriceSearchRequest priceSearchRequest);
}
