package com.se.product.service.controller.api;


import com.se.product.service.exception.model.ErrorResponse;
import com.se.product.service.model.request.CategoriesRequest;
import com.se.product.service.model.request.PricesRequest;
import com.se.product.service.model.request.ProductRequest;
import com.se.product.service.model.response.ProductResponse;
import com.se.product.service.model.search.PagedProductSearchRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Tag(name = "Product Api",
        description = "REST Operations about product")
public interface ProductApi {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Successfully created item",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductResponse.class))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Incorrect request parameters",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "401",
                    description = "You are not authorized to view the resource",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "403",
                    description = "Accessing the resource you were trying to reach is forbidden",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "409",
                    description = "Incorrect request param",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "422",
                    description = "Incorrect model to create", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            }),

            @ApiResponse(responseCode = "415",
                    description = "Incorrect model type to create", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            }),
    })
    @Operation(summary = "Adds an item to the system",
            description = "adds a product item")
    ResponseEntity<ProductResponse> create(
            @Parameter(name = "product",
                    description = "The values to new product entity", required = true)
            @RequestBody @Valid ProductRequest product);


    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Successfully created item",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductResponse.class))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Incorrect request parameters",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "401",
                    description = "You are not authorized to view the resource",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "403",
                    description = "Accessing the resource you were trying to reach is forbidden",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "409",
                    description = "Incorrect request param",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "415",
                    description = "Incorrect model type to create", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = "422",
                    description = "Incorrect model to create", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @Operation(summary = "update product", description = "This method update existed product")
    ResponseEntity<ProductResponse> update(
            @Parameter(name = "id",
                    description = "Product unique identifier",
                    required = true, example = "123")
            @PathVariable(value = "id") @Valid @NotNull Long id,

            @Parameter(
                    name = "requestModel",
                    description = "Product object that needs to be changed", required = true)
            @Valid @RequestBody ProductRequest requestModel);


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully, describe product",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductResponse.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Incorrect request parameters",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "401",
                    description = "You are not authorized to view the resource",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "403",
                    description = "Accessing the resource you were trying to reach is forbidden",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "The resource you were trying to update is not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "Get product", description = "Get product full information.")
    ResponseEntity<ProductResponse> getById(
            @Parameter(name = "id",
                    description = "Product unique identifier",
                    required = true, example = "123")
            @PathVariable(value = "id") @Valid @NotNull Long id);


    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",
                    description = "Item successfully deleted"),
            @ApiResponse(responseCode = "401",
                    description = "You are not authorized to view the resource",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "403",
                    description = "Accessing the resource you were trying to reach is forbidden",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "The resource you were trying to update is not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Incorrect request parameters",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "Delete", description = "Delete product by unique identifier.")
    ResponseEntity delete(
            @Parameter(name = "id",
                    description = "Product unique identifier",
                    required = true, example = "123")
            @PathVariable(value = "id") @Valid @NotNull Long id);


    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item successfully updated",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductResponse.class))
                    }),
            @ApiResponse(responseCode = "401",
                    description = "You are not authorized to view the resource",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "403",
                    description = "Accessing the resource you were trying to reach is forbidden",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "The resource you were trying to update is not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Incorrect request parameters",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),

            @ApiResponse(responseCode = "422", description = "Incorrect model to create",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),
    })
    @Operation(summary = "Update categories", description = "Update products categories")
    ResponseEntity<ProductResponse> updateCategory(
            @Parameter(name = "id",
                    description = "product unique identifier",
                    required = true, example = "1")
            @PathVariable(value = "id") @NotNull Long id,

            @Parameter(name = "categories",
                    description = "category unique identifier",
                    required = true, example = "123")
            @RequestBody @Valid CategoriesRequest categoriesRequest);


    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Item successfully updated",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductResponse.class))
                    }),
            @ApiResponse(responseCode = "401",
                    description = "You are not authorized to view the resource",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "403",
                    description = "Accessing the resource you were trying to reach is forbidden",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "The resource you were trying to update is not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),

            @ApiResponse(responseCode = "400",
                    description = "Incorrect request parameters",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),

            @ApiResponse(responseCode = "422", description = "Incorrect model to create",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),
    })
    @Operation(summary = "prices", description = "update price")
    ResponseEntity<ProductResponse> updatePrices(
            @Parameter(name = "product",
                    description = "product unique identifier",
                    required = true, example = "123")
            @PathVariable(value = "id")@Valid @NotNull Long id,

            @Parameter(name = "categories",
                    description = "category model to updates",
                    required = true)
            @RequestBody @Valid PricesRequest pricesRequest);




    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully list of all items"),
            @ApiResponse(responseCode = "401",
                    description = "You are not authorized to view the resource",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "403",
                    description = "Accessing the resource you were trying to reach is forbidden",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),})

    @Operation(summary = "list", description = "All available products.")
    ResponseEntity<?> getAll();

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully list of all items"),
            @ApiResponse(responseCode = "401",
                    description = "You are not authorized to view the resource",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "403",
                    description = "Accessing the resource you were trying to reach is forbidden",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),})

    @Operation(summary = "paged", description = "Paged product list information")
    ResponseEntity<?> paged(@Valid @RequestBody PagedProductSearchRequest searchRequest);
}
