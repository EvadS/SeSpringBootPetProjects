package com.se.services.elasticsearch.controller;


import com.se.services.elasticsearch.dao.ProductDao;
import com.se.services.elasticsearch.document.Product;
import com.se.services.elasticsearch.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

import static com.se.services.elasticsearch.util.PageSupport.DEFAULT_PAGE_SIZE;
import static com.se.services.elasticsearch.util.PageSupport.FIRST_PAGE_NUM;

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

    @ResponseBody
    @GetMapping("/search")
    public List<Product> search(
            @RequestParam(name = "field", defaultValue = "") String field,
            @RequestParam(name = "value", defaultValue = "") String value){
        return productDao.searchProduct( field,  value);
    }

    @Operation(description = "Search for product(s).")
    @GetMapping(path = "/search2")
    public Page<Product> find(
            @PositiveOrZero @RequestParam(name = "page", defaultValue = FIRST_PAGE_NUM) int page,
            @Positive @Max(value = Constants.MAX_SIZE_DEFAULT) @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size,
            @RequestParam(name = "sort", defaultValue = "createdOn:DESC") String sort,
            @RequestParam(required = false, name = "search") String search
    ){
        return productDao.fetch(PageRequest.of(page, size), search, sort);
    }
}
