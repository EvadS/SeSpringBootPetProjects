package com.se.sample.router;


import com.se.sample.constants.GeneralConstants;
import com.se.sample.errors.model.ErrorDetail;
import com.se.sample.handler.ProductHandler;
import com.se.sample.models.request.ProductRequest;
import com.se.sample.models.response.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
@Tag(name = "PersonRouter", description = "Person Router functional API")
public class PersonRouter {

    @RouterOperations({
            @RouterOperation(
                    path = "/gets", beanClass = ProductHandler.class, beanMethod = "findAll",
                    operation = @Operation(
                            operationId = "opGetData",
                            summary = "get all products", description = "get all products description",
                            method = "GET", tags = "Route operations", responses = {
                            @ApiResponse(responseCode = "404", description = "Employee not found")
                    })),
            @RouterOperation(
                    path = "/create", beanClass = ProductHandler.class, beanMethod = "save",
                    operation = @Operation(
                            operationId = "crate_products",
                            summary = "crate products", description = "Create new product",
                            method = "POST", tags = "Route operations",
                            requestBody = @RequestBody(required = true,
                                    content = @Content(
                                            schema = @Schema(implementation = ProductRequest.class))),
                            responses = {
                                    @ApiResponse(responseCode = "201", description = GeneralConstants.HTTP_OK_CREATED,
                                            content = @Content(
                                                    mediaType = "application/json",
                                                    schema = @Schema(implementation = ProductResponse.class))),
                                    @ApiResponse(responseCode = "4xx", description = "Error retrieving Product data",
                                            content = @Content(
                                                    mediaType = "application/json",
                                                    schema = @Schema(implementation = ErrorDetail.class)))
                            })),
            @RouterOperation(
                    path = "/get/{id}", beanClass = ProductHandler.class, beanMethod = "findById",
                    operation = @Operation(
                            operationId = "get_by_id",
                            summary = "get by id", description = "Get product by unique identifier",
                            method = "Get", tags = "Route operations",
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Successfully updated Product data",
                                            content = @Content(
                                                    mediaType = "application/json",
                                                    schema = @Schema(implementation = ProductResponse.class))),

                                    @ApiResponse(responseCode = "404", description = GeneralConstants.HTTP_NOT_FOUND,
                                            content = @Content(mediaType = "application/json",
                                                    schema = @Schema(implementation = ErrorDetail.class)))
                            }
                    ))
    })

    @Bean
    public RouterFunction<ServerResponse> route(ProductHandler handler) {
        return RouterFunctions

                .route(GET("/gets").and(accept(MediaType.APPLICATION_JSON)), handler::findAll)
                .andRoute(GET("/get/{id}").and(accept(MediaType.APPLICATION_STREAM_JSON)), handler::findById)
                .andRoute(POST("/create").and(accept(MediaType.APPLICATION_JSON)), handler::save)
                .andRoute(DELETE("/delete/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::delete);
    }
}
