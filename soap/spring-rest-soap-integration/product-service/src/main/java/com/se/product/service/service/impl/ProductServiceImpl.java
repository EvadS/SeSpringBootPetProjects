package com.se.product.service.service.impl;

import com.se.product.service.domain.Product;
import com.se.product.service.exception.AlreadyExistException;
import com.se.product.service.mapper.ProductMapper;
import com.se.product.service.model.payload.ProductRequest;
import com.se.product.service.model.payload.ProductResponse;
import com.se.product.service.repository.ProductRepository;
import com.se.product.service.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ProductResponse create(ProductRequest productRequest) {

        boolean exists = productRepository.existsByName(productRequest.getName());

        if(exists){
            throw  new AlreadyExistException("Product", "name", productRequest.getName());
        }
        Product product = ProductMapper.MAPPER.toProduct(productRequest);

        ProductResponse productResponse = ProductMapper.MAPPER.toProductRepository(product);
        return productResponse;
    }
}
