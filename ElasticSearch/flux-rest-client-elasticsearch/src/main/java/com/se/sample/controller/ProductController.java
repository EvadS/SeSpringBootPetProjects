package com.se.sample.controller;

import com.se.sample.model.Product;
import com.se.sample.repository.ProductRepository;
import com.se.sample.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;


@Tag(name = "Product API",
        description = "Provide async operation with elastic ...")
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    @PostMapping
    public Mono<IndexResponse> addProduct(@RequestBody Product product) throws IOException {
        return productRepository.create(product);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Product>> updateById(@PathVariable String id, @RequestBody Product user){
        return productService.updateItem(id,user)
                .map(updatedUser -> ResponseEntity.ok(updatedUser))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Product>> getProductById(@PathVariable (value = "id")String productId){
        return productService.getById(productId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUserById(@PathVariable (value = "id") String  id){
        return productService.deleteItem(id)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
