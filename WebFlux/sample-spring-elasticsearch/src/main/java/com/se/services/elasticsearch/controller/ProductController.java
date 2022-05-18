package com.se.services.elasticsearch.controller;


import com.se.services.elasticsearch.dao.ProductDao;
import com.se.services.elasticsearch.document.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductDao productDao;

    @PostMapping
    public ResponseEntity createProfile(@RequestBody Product product) throws Exception {
        return new ResponseEntity(productDao.createProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable String id) throws Exception {
        return productDao.getProduct(id);
    }

}
