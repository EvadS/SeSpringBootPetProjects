package com.se.sample.controller;


import com.se.sample.entity.dto.ProductResponse;
import com.se.sample.entity.model.Product;
import com.se.sample.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/getAll")
    public Flux<Product> getAll() {
        return productService.getAll();
    }

    //    public Mono<ResponseEntity<Bucket>> getBucketById(@PathVariable(value = "id") String bucketId) {
//        return bucketRepository.findById(bucketId)
//                .map(saveBucket -> ResponseEntity.ok(saveBucket))
//                .defaultIfEmpty(ResponseEntity.notFound().build());
//            .switchIfEmpty(Mono.error(new BucketNotFoundException("Data not found")));
//            .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//    }

    @GetMapping("/get/{id}")
    public Mono<ResponseEntity<ProductResponse>> getById(@PathVariable("id") final String id) {
        return productService.getById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<ProductResponse>> updateById(@PathVariable("id") final String id, @RequestBody final Product product) {

        return productService.update(id,product)
                .map(updatedUser -> ResponseEntity.ok(updatedUser))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono save(@RequestBody final Product product) {
        return productService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public Mono delete(@PathVariable final String id) {
        return productService.delete(id);

        /*
           return userService.deleteUser(userId)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
         */
    }

}
