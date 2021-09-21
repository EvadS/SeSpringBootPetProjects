package com.se.sample.controller;


import com.se.sample.entity.dto.ProductResponse;
import com.se.sample.entity.dto.response.ProductItemResponse;
import com.se.sample.entity.dto.response.ProductRequest;
import com.se.sample.entity.mapper.ProductMapper;
import com.se.sample.entity.model.Product;
import com.se.sample.helper.PageSupport;
import com.se.sample.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// TODO: move to properties
import javax.validation.Valid;

import java.util.List;

import static com.se.sample.helper.PageSupport.FIRST_PAGE_NUM;
import static com.se.sample.helper.PageSupport.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController implements  IProductController {

    private final ProductService productService;
//
//    @GetMapping("/getAll")
//    public Flux<Product> getAll() {
//        return productService.getAll();
//    }
//
//    //    public Mono<ResponseEntity<Bucket>> getBucketById(@PathVariable(value = "id") String bucketId) {
////        return bucketRepository.findById(bucketId)
////                .map(saveBucket -> ResponseEntity.ok(saveBucket))
////                .defaultIfEmpty(ResponseEntity.notFound().build());
////            .switchIfEmpty(Mono.error(new BucketNotFoundException("Data not found")));
////            .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
////    }
//
//
//
//    @PutMapping("/update/{id}")
//    public Mono<ResponseEntity<ProductResponse>> updateById(@PathVariable("id") final String id, @RequestBody final Product product) {
//
//        return productService.update(id,product)
//                .map(updatedUser -> ResponseEntity.ok(updatedUser))
//                .defaultIfEmpty(ResponseEntity.badRequest().build());
//    }
//
//    @PostMapping("/create")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Mono save(@Valid  @RequestBody final ProductRequest productRequest) {
//        return productService.save(productRequest);
//    }
//

//
//
//    //@Apioperation ("importing all data from the database")
//    @GetMapping("/paged")
//    public Mono<PageSupport<ProductResponse>> getEntitiesPage(
//            @RequestParam(name = "page", defaultValue = FIRST_PAGE_NUM) int page,
//            @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size
//    ) {
//
//        Mono<PageSupport<ProductResponse>> pageResponse = productService.getPageResponse(PageRequest.of(page, size));
//        return pageResponse;
//    }
//
    @GetMapping("/{id}")
    @Override
    public Mono<ResponseEntity<ProductResponse>> getProductById(@PathVariable("id") final String id) {
        return productService.getById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Override
    public Mono<ResponseEntity<ProductResponse>> save(@Valid  @RequestBody final ProductRequest productRequest) {
        return productService.save(productRequest);
    }

    @DeleteMapping("/{id}")
    @Override
    public Mono<ResponseEntity<Void>> deleteEmployeeById(@PathVariable("id") String id) {
     return   productService.delete(id)
                .map(r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @GetMapping("/all")
    @Override
    public Mono<List<ProductItemResponse>> getall() {

        Mono<List<ProductItemResponse>> listMono = productService.getAll()
                .collectList();

        return listMono;
    }

    @GetMapping("/paged")
    @Override
    public Mono<PageSupport<ProductResponse>> paged(  @RequestParam(name = "page", defaultValue = FIRST_PAGE_NUM) int page,
                                                      @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size) {
             Mono<PageSupport<ProductResponse>> pageResponse = productService.getPageResponse(PageRequest.of(page, size));
        return pageResponse;
    }
}
