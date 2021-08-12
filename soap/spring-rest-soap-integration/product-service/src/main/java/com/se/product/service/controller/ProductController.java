package com.se.product.service.controller;

import com.se.product.service.controller.api.ProductApi;
import com.se.product.service.domain.Product;
import com.se.product.service.mapper.ProductMapper;
import com.se.product.service.model.request.CategoriesRequest;
import com.se.product.service.model.request.PricesRequest;
import com.se.product.service.model.response.ProductItemResponse;
import com.se.product.service.model.request.PagedProductSearchRequest;
import com.se.product.service.model.request.ProductRequest;
import com.se.product.service.model.response.ProductResponse;
import com.se.product.service.repository.ProductRepository;
import com.se.product.service.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/product" )
public class ProductController implements ProductApi {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;
    private final   ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @Override
    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest product) {
        logger.debug("Create new product: {}", product);

        ProductResponse response = productService.create(product);
        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable(value = "id") @NotNull Long id,
            @RequestBody @Valid ProductRequest product) {

        logger.debug("update product. id:{}, model:{}", id, product);

        ProductResponse response = productService.update(id, product);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable(value = "id") @NotNull Long id) {

        logger.debug("Handle get product details request, id: {}", id);

        ProductResponse productResponse = productService.get(id);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity delete(
            @PathParam(value = "id") @NotNull Long id) {
        logger.debug("Handle delete product request, id: {}", id);

        productService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @Override
    @PutMapping("/category/{id}")
    public ResponseEntity<ProductResponse> updateCategory(
            @PathParam(value = "id") @NotNull Long id,
            @RequestBody @Valid CategoriesRequest categoriesRequest) {

        logger.debug("Handle change product categories request, id: {}, categories: {}", id, categoriesRequest);

        ProductResponse productResponse = productService.updateCategories(id, categoriesRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.ACCEPTED);
    }

    @Override
    @PutMapping("/price/{id}")
    public ResponseEntity<ProductResponse> updatePrices(
            @PathParam(value = "id") @NotNull Long id,
            @RequestBody @Valid PricesRequest pricesRequest) {

        logger.debug("Handle change product prices request, id: {}, pricesRequest: {}", id, pricesRequest);

        ProductResponse productResponse = productService.updatePrices(id, pricesRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.ACCEPTED);
    }


    @PostMapping("/paged")
    @Override
    public ResponseEntity<?> paged(@Valid @RequestBody PagedProductSearchRequest searchRequest){
        logger.debug("Handle get paged products list");

        Page<ProductItemResponse> paged = productService.getPaged(searchRequest);
        return ResponseEntity.ok(paged);
    }



    @Override
    @GetMapping("/list")
    public  ResponseEntity<?> getAll(){

        List<Product> all = productRepository.findAll();

        List<ProductResponse> collect = all.stream()
                .map(ProductMapper.MAPPER::toProductResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(collect);
    }
}
