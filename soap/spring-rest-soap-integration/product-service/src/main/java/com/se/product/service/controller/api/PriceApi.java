package com.se.product.service.controller.api;

import com.se.product.service.exception.model.ErrorResponse;
import com.se.product.service.model.request.PriceRequest;
import com.se.product.service.model.response.CategoryResponse;
import com.se.product.service.model.response.PriceResponse;
import com.se.product.service.model.search.PriceSearchRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@Tag(name = "Price Api",
        description = "REST Operations about Price entity")
public interface PriceApi {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created price",
                     content = {
                     @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PriceResponse.class))
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
                    description = "Incorrect model to create",
                    content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = "415",
                    description = "Incorrect model type to create",
                    content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @Operation(summary = "Adds an item to the system",
            description = "adds a price item")
    ResponseEntity<PriceResponse> create(@Valid @RequestBody PriceRequest request);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",
                    description = "Successfully updated item",
                    content = {
                    @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryResponse.class))
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
            @ApiResponse(responseCode = "400",
                    description = "Incorrect request parameters",
                    content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "The resource you were trying to reach is not found",
                    content = {
                    @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "Update price.", description = "Update price.")
    ResponseEntity<PriceResponse> update(
            @Parameter(
                    name = "priceID",
                    description = "price unique identifier",
                    required = true, example = "123")
            @PathVariable(value = "id") @NotNull Long priceID,

            @Parameter(
                    name = "requestModel",
                    description = "Price object that needs to be changed", required = true)
            @Valid @RequestBody PriceRequest requestModel);


    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",
                    description = "Successfully deleted price"),

            @ApiResponse(responseCode = "400",
                    description = "Incorrect request parameters",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}),
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
    @Operation(summary = "Delete price.", description = "Delete price by unique identifier.")
    ResponseEntity<?> deletePrice(
            @Parameter(name = "id",
                    description = "category unique identifier",
                    required = true, example = "123")
            @PathVariable(value = "id") @NotNull Long id);



    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully, describe price info ",
                    content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PriceResponse.class))
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
                                    schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "Price.", description = "Get price by id.")
    ResponseEntity<PriceResponse> getById(
            @Parameter(name = "id",
                    description = "Price unique identifier", required = true, example = "123")
            @PathVariable(value = "id") Long id);



    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully list of all prices",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = PriceResponse.class)))
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
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    @Operation(summary = "Current prices",description = "Prices list.")
    ResponseEntity<List<PriceResponse>> list();

    // TODO:S
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully paged list of all prices"),

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
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    @Operation(summary = "Current prices",
            description = "Prices list with pagination.", tags = {})
    public ResponseEntity<Page<PriceResponse>> getPaged(
            @RequestBody @Valid PriceSearchRequest request);
}
