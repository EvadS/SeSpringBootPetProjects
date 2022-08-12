package com.se.product.service.controller.api;

import com.se.product.service.exception.model.ErrorResponse;
import com.se.product.service.model.request.CategoryRequest;
import com.se.product.service.model.response.CategoryResponse;
import com.se.product.service.model.response.CategoryResponseList;
import com.se.product.service.model.search.CategorySearch;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Tag(name = "Category Api",
        description = "REST Operations about category")
public interface CategoryApi {
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Successfully created item",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryResponse.class))
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
            description = "adds a category item")
    ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest request);


    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",
                    description = "Successfully updated item",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryResponse.class))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Incorrect request parameters",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "You are not authorized to view the resource",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(
                    responseCode = "403",
                    description = "Accessing the resource you were trying to reach is forbidden",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "The resource you were trying to reach is not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "The resource you were trying to reach is not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(
                    responseCode = "422",
                    description = "Incorrect model to create",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),

            @ApiResponse(responseCode = "400",
                    description = "Incorrect request parameters",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    })
    })
    @Operation(summary = "Update category.", description = "update existing category.")
    ResponseEntity<CategoryResponse> update(
            @Parameter(
                    name = "categoryID",
                    description = "category unique identifier",
                    required = true, example = "123")
            @PathVariable(value = "id") @NotNull Long categoryID,
            @Parameter(
                    name = "requestModel",
                    description = "Category object that needs to be changed", required = true)
            @Valid @RequestBody CategoryRequest requestModel);


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully updated item",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryResponse.class))}),

            @ApiResponse(
                    responseCode = "401",
                    description = "You are not authorized to view the resource",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(
                    responseCode = "403",
                    description = "Accessing the resource you were trying to reach is forbidden",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}), @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found"),
            @ApiResponse(responseCode = "404",
                    description = "The resource you were trying to update is not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Incorrect request parameters",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),
    })
    @Operation(summary = "Change base category id", description = "Change base category id.")
    ResponseEntity<CategoryResponse> changeBase(
            @Parameter(name = "id",
                    description = "Category unique identifier",
                    required = true,
                    example = "123")
            @PathVariable(value = "id") @NotNull Long categoryID,
            @Parameter(name = "baseId",
                    description = "base category id",
                    required = true, example = "123")
            @PathVariable(value = "base-id") @NotNull Long baseId);


    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "202",
                    description = "Item successfully deleted"),
            @ApiResponse(
                    responseCode = "401",
                    description = "You are not authorized to view the resource",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(
                    responseCode = "403",
                    description = "Accessing the resource you were trying to reach is forbidden",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404",
                    description = "The resource you were trying to reach is not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Incorrect request parameters",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "Delete category", description = "Delete price by inique identifier")
    ResponseEntity deleteItem(
            @Parameter(name = "id",
                    description = "category unique identifier",
                    required = true, example = "123")
            @PathVariable(value = "id") @NotNull Long id);


    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully, describe category",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryResponse.class))}),
            @ApiResponse(responseCode = "404",
                    description = "The resource you were trying to reach is not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(
                    responseCode = "401",
                    description = "You are not authorized to view the resource",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(
                    responseCode = "403",
                    description = "Accessing the resource you were trying to reach is forbidden",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}), @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Incorrect request parameters",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}), @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found"),
    })
    @Operation(summary = "Categories", description = "Get category by id.")
    ResponseEntity<CategoryResponse> getById(
            @Parameter(name = "id",
                    description = "Category unique identifier", required = true, example = "123")
            @PathVariable(value = "id") Long id);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully list of all items",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryResponseList.class))}),
            @ApiResponse(
                    responseCode = "401",
                    description = "You are not authorized to view the resource",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(
                    responseCode = "403",
                    description = "Accessing the resource you were trying to reach is forbidden",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),
    })
    @GetMapping(value = "/list")
    @Operation(summary = "list", description = "Current categories.")
    ResponseEntity<CategoryResponseList> list();


    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully list of all items",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryResponseList.class))}),
            @ApiResponse(
                    responseCode = "401",
                    description = "You are not authorized to view the resource",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(
                    responseCode = "403",
                    description = "Accessing the resource you were trying to reach is forbidden",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),
    })
    @Operation(summary = "search", description = "Current categories by search param.")
    Page<CategoryResponse> search(CategorySearch request, Pageable pageable);
}