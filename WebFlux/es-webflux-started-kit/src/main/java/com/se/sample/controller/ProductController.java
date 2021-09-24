package com.se.sample.controller;

import com.se.sample.controller.api.ProductApi;
import com.se.sample.helper.PageSupport;
import com.se.sample.models.filter.ESSearchFilter;
import com.se.sample.models.request.ProductItemResponse;
import com.se.sample.models.request.ProductRequest;
import com.se.sample.models.response.ProductResponse;
import com.se.sample.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

import static com.se.sample.helper.PageSupport.DEFAULT_PAGE_SIZE;
import static com.se.sample.helper.PageSupport.FIRST_PAGE_NUM;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@Tag(name = "Product", description = "the product API with documentation annotations")
public class ProductController implements ProductApi {

    private final ProductService productService;

    @Override
    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProductResponse>> getOne(@PathVariable("id") final String id) {
        return productService.getById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Mono<ResponseEntity<ProductResponse>> save(@Valid @RequestBody final
                                                      ProductRequest productRequest) {
        Mono savedProduct = productService.save(productRequest);
        Mono response = savedProduct.map(ResponseEntity::ok);

        return response;
    }

    @PutMapping("/{id}")
    @Override
    public Mono<ResponseEntity<ProductResponse>> update(@Valid
                                                        @NotBlank(message = "id is required field") @PathVariable("id") final String id,
                                                        @RequestBody final ProductRequest productRequest) {
        return productService.update(id, productRequest)
                .map(u -> ResponseEntity.ok(u));
        // .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @Override
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable("id") String id) {
        return productService.delete(id)
                .map(r -> ResponseEntity.ok().<Void>build());
    }

    @Override
    @GetMapping("/all")
    public Mono<ResponseEntity<List<ProductItemResponse>>> getAll() {
        return productService.getAll()
                .collectList()
                .map(dtos -> ResponseEntity.status(HttpStatus.OK).body(dtos));
    }

    @GetMapping("/paged")
    @Override
    public Mono<ResponseEntity<PageSupport<ProductResponse>>> paged(@RequestParam(name = "page", defaultValue = FIRST_PAGE_NUM) int page,
                                                                    @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size) {
        Mono<PageSupport<ProductResponse>> pageResponse = productService.getPageResponse(PageRequest.of(page, size));

        Mono<ResponseEntity<PageSupport<ProductResponse>>> monPageResponse = pageResponse.
                map(r -> ResponseEntity.ok(r));
        return monPageResponse;
    }

    @PostMapping(value = "/search")
    @Override
    public ResponseEntity<Page<ProductResponse>> search(@RequestParam(name = "page", defaultValue = FIRST_PAGE_NUM) int page,
                                                        @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size,
                                                        @RequestBody(required = false) ESSearchFilter esSearchFilter) {
       System.out.println("NOT IMPLENTED");
       return null;
    }
}