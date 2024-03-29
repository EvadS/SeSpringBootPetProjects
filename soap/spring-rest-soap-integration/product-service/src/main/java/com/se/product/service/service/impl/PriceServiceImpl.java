package com.se.product.service.service.impl;

import com.se.product.service.domain.Price;
import com.se.product.service.domain.specification.PriceSpecification;
import com.se.product.service.exception.DuplicateException;
import com.se.product.service.exception.model.ResourceNotFoundException;
import com.se.product.service.mapper.PriceMapper;
import com.se.product.service.model.payload.PriceSearchModel;
import com.se.product.service.model.request.PriceRequest;
import com.se.product.service.model.response.PriceResponse;
import com.se.product.service.model.search.PriceSearchRequest;
import com.se.product.service.repository.PriceRepository;
import com.se.product.service.service.PriceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final PriceSpecification priceSpecification;


    public PriceServiceImpl(PriceRepository priceRepository, PriceSpecification priceSpecification) {
        this.priceRepository = priceRepository;
        this.priceSpecification = priceSpecification;
    }

    @Override
    public PriceResponse create(PriceRequest request) {
        boolean exists = priceRepository.existsByCostAndCurrencyType(request.getCost(), request.getCurrencyType());

        if (exists) {
            HashMap<String, String> stringStringHashMap = new HashMap<>();
            stringStringHashMap.put("cost", String.valueOf(request.getCost()));
            stringStringHashMap.put("currency", String.valueOf(request.getCurrencyType()));

            throw new DuplicateException("Price", stringStringHashMap);

        }

        Price price = PriceMapper.MAPPER.toPrice(request);
        priceRepository.save(price);

        return PriceMapper.MAPPER.toPriceResponse(price);
    }

    @Override
    public PriceResponse updatePrice(Long priceId, PriceRequest requestModel) {
        return priceRepository.findById(priceId)
                .map(price -> {
                    price.setCost(requestModel.getCost());
                    price.setCurrencyType(requestModel.getCurrencyType());
                    priceRepository.save(price);
                    return PriceMapper.MAPPER.toPriceResponse(price);
                }).orElseThrow(() -> new ResourceNotFoundException("Price", "id", requestModel));
    }

    @Override
    public void deletePrice(Long id) {
        Price price = priceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Price", "id", id));

        priceRepository.delete(price);
    }

    @Override
    public PriceResponse getById(Long priceId) {
        return priceRepository.findById(priceId)
                .map(price -> PriceMapper.MAPPER.toPriceResponse(price))
                .orElseThrow(() -> new ResourceNotFoundException("Price", "id", priceId));
    }

    @Override
    public List<PriceResponse> getAll() {
        return priceRepository.findAll()
                .stream()
                .map(PriceMapper.MAPPER::toPriceResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<PriceResponse> getPaged(PriceSearchRequest priceSearchRequest) {

        Pageable pageable = PageRequest.of(
                priceSearchRequest.getPageNum(),
                priceSearchRequest.getPageSize(),
                Sort.by("createdAt").descending());

        PriceSearchModel priceModel = PriceMapper.MAPPER.toPriceSearchModel(priceSearchRequest);

        Page<PriceResponse> map = priceRepository.findAll(priceSpecification
                .getFilter(priceModel), pageable)
                .map(PriceMapper.MAPPER::toPriceResponse);

        return map;
    }
}
