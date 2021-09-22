package com.se.sample.controller;


import com.se.sample.models.filter.ESSearchFilter;
import com.se.sample.models.response.ProductResponse;
import com.se.sample.models.request.ProductItemResponse;
import com.se.sample.models.request.ProductRequest;
import com.se.sample.helper.PageSupport;
import com.se.sample.service.ProductService;
import lombok.AllArgsConstructor;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

// TODO: move to properties
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import java.util.List;

import static com.se.sample.helper.PageSupport.FIRST_PAGE_NUM;
import static com.se.sample.helper.PageSupport.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController implements  IProductController {

    private final ProductService productService;


    @GetMapping("/{id}")
    @Override
    public Mono<ResponseEntity<ProductResponse>> getProductById(@PathVariable("id") final String id) {
        return productService.getById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Override
    public Mono<ResponseEntity<ProductResponse>> save(@Valid  @RequestBody final
                                                          ProductRequest productRequest) {
        return productService.save(productRequest);
    }

    @PutMapping("/{id}")
    @Override
    public Mono<ResponseEntity<ProductResponse>> update(@Valid
            @NotBlank(message = "id is required field")  @PathVariable("id") final String id,
            @RequestBody final ProductRequest productRequest) {

         return productService.update(id,productRequest)
             .map(u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.badRequest().build());

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

    @PostMapping(value = "/search")
    @Override
    public ResponseEntity<Page<ProductResponse>> searchProducts(@RequestParam(name = "page", defaultValue = FIRST_PAGE_NUM) int page,
                                                                @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size
            ,
                                                                @RequestBody(required = false) ESSearchFilter esSearchFilter) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<ProductResponse> result = null;
        if (esSearchFilter == null) {
            result = productService.findAll(pageRequest);
        } else {
            result = productService.searchProductByCriteria(esSearchFilter,  pageRequest);
        }
        return ResponseEntity.ok(result);
    }
}
