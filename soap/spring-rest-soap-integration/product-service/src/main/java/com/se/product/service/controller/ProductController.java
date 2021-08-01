package com.se.product.service.controller;

import com.se.product.service.controller.base.ProductControllerBase;
import com.se.product.service.model.payload.ProductRequest;
import com.se.product.service.model.payload.ProductResponse;
import com.se.product.service.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController implements ProductControllerBase {
    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "Add a product")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody @Valid ProductRequest product){

        ProductResponse response = productService.create(product);
        return ResponseEntity.ok(response);
    }

}
