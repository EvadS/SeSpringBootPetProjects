package com.se.sample.service;

import com.se.sample.entity.Product;
import com.se.sample.repository.ProductRepository;
import com.se.sample.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRespository) {
        this.productRepository = productRespository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product stock) throws ResourceNotFoundException {


        Product employee = productRepository.findById(stock.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + stock.getId()));

        return productRepository.save(stock);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
