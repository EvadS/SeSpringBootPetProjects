package com.sample.rest.server.web.controller;


import com.sample.rest.server.domain.Product;
import com.sample.rest.server.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@Api(value="onlinestore", description="Operations pertaining to products in Online Store")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "View a list of available products",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Iterable<Product>> list(Model model){
        Iterable<Product> productList = productService.listAllProducts();
        return ResponseEntity.ok(productList);
    }
    @ApiOperation(value = "Search a product with an ID",response = Product.class)
    @RequestMapping(value = "/show/{id}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Product> showProduct(@PathVariable Integer id, Model model){
       Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @ApiOperation(value = "Add a product")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Product> saveProduct(@RequestBody Product productRequest){
        Product product = productService.saveProduct(productRequest);
        return ResponseEntity.ok(product);
    }

    @ApiOperation(value = "Update a product")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody Product productREquest){
        Product storedProduct = productService.getProductById(id);
        storedProduct.setDescription(productREquest.getDescription());
        storedProduct.setImageUrl(productREquest.getImageUrl());
        storedProduct.setPrice(productREquest.getPrice());
        storedProduct =  productService.saveProduct(storedProduct);
        return ResponseEntity.ok(storedProduct);
    }

    @ApiOperation(value = "Delete a product")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable Integer id){
        productService.deleteProduct(id);
        return ResponseEntity.accepted().build();
    }
}
