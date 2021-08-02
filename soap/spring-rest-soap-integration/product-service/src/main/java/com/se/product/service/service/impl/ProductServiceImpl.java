package com.se.product.service.service.impl;

import com.se.product.service.domain.Product;
import com.se.product.service.domain.specification.ProductSearch;
import com.se.product.service.domain.specification.ProductSpecification;
import com.se.product.service.exception.AlreadyExistException;
import com.se.product.service.exception.model.ResourceNotFoundException;
import com.se.product.service.mapper.ProductMapper;
import com.se.product.service.model.CategoriesRequest;
import com.se.product.service.model.PricesRequest;
import com.se.product.service.model.ProductItemResponse;
import com.se.product.service.model.payload.ProductRequest;
import com.se.product.service.model.payload.ProductResponse;
import com.se.product.service.model.payload.ProductSearchRequest;
import com.se.product.service.repository.CategoryRepository;
import com.se.product.service.repository.PriceRepository;
import com.se.product.service.repository.ProductRepository;
import com.se.product.service.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PriceRepository priceRepository;

    private final ProductSpecification productSpecification;


    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, PriceRepository priceRepository, ProductSpecification productSpecification) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
        this.productSpecification = productSpecification;
    }

    @Override
    public ProductResponse create(ProductRequest productRequest) {

        boolean exists = productRepository.existsByName(productRequest.getName());

        if(exists){
            throw  new AlreadyExistException("Product", "name", productRequest.getName());
        }

        // TODO check is prises exists -> maybe should return list ?
        if(productRequest.getPrices() != null ){
            // TODO: how to use stream ?
            for(Long item : productRequest.getPrices().getPrices() ){
                priceRepository.findById(item).orElseThrow(
                        () -> new ResourceNotFoundException("Price", "id", item));
            }
        }

        if(productRequest.getCategories() != null &&
                productRequest.getCategories().getCategories() != null){
            // TODO: how to use stream ?
            for(Long item : productRequest.getCategories().getCategories() ){
                categoryRepository.findById(item).orElseThrow(
                        () -> new ResourceNotFoundException("Category", "id", item));
            }
        }

        Product product = ProductMapper.MAPPER.toProduct(productRequest);

        ProductResponse productResponse = ProductMapper.MAPPER.toProductRepository(product);
        return productResponse;
    }

    @Override
    public ProductResponse update(Long id, ProductRequest product) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ProductResponse updateCategories(Long id, CategoriesRequest categoriesRequest) {
        return null;
    }

    @Override
    public ProductResponse updatePrices(Long id, PricesRequest pricesRequest) {
        return null;
    }

    @Override
    public Page<ProductItemResponse> getPagged(ProductSearchRequest searchRequest) {

        Pageable pageable = PageRequest.of(
                searchRequest.getPage(),
                searchRequest.getCount(),
                Sort.by("createdAt").descending());

        ProductSearch productSearch = ProductMapper.MAPPER.toProductSearch(searchRequest);

        Page<ProductItemResponse> productItemResponses = productRepository.findAll(productSpecification
                .getFilter(productSearch), pageable)
                .map(ProductMapper.MAPPER::toProductItemResponse);

        return productItemResponses;
    }
}
