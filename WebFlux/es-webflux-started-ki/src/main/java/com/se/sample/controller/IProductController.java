package com.se.sample.controller;


import com.se.sample.models.filter.ESSearchFilter;
import com.se.sample.models.response.ProductResponse;
import com.se.sample.models.request.ProductItemResponse;
import com.se.sample.models.request.ProductRequest;
import com.se.sample.errors.ErrorDetail;
import com.se.sample.helper.PageSupport;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.se.sample.helper.PageSupport.DEFAULT_PAGE_SIZE;
import static com.se.sample.helper.PageSupport.FIRST_PAGE_NUM;

public interface IProductController {
//
    @Operation(
            summary = "Get Product By Id",
            description = "Allow to get Product by Id",
            method = "GET",

            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved Product data",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProductResponse.class))),
                    @ApiResponse(responseCode = "4xx", description = "Error retrieving Employee data",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProductResponse.class)))})
    Mono<ResponseEntity<ProductResponse>> getProductById(@PathVariable(name = "id") final String id);


    @Operation(
            summary = "Save Product",
            description = "Allow to save Products",
            method = "POST",

            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductRequest.class)),
                    required = true),

            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully saved Product data",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProductResponse.class))),
                    @ApiResponse(responseCode = "4xx", description = "Error retrieving Product data",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDetail.class)))})
    Mono<ResponseEntity<ProductResponse>> save(@RequestBody final ProductRequest productRequest);


        @Operation(
            summary = "Delete Product By Id",
            description = "Allow to delete Product by Id",
            method = "DELETE",

            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully deleted Product data",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Void.class))),
                    @ApiResponse(responseCode = "4xx", description = "Error retrieving Product data",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDetail.class)))})
        Mono<ResponseEntity<Void>> deleteEmployeeById(@PathVariable(name = "id") final String id);



    @Operation(
            summary = "Get All ",
            description = "Allow to get all products ",
            method = "GET",

            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved Product  data",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProductItemResponse.class))),
                    @ApiResponse(responseCode = "4xx", description = "Error retrieving product  data",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDetail.class)))})
    Mono<List<ProductItemResponse>> getall();

    @Operation(
            summary = "paged All ",
            description = "Allow to get paged  products ",
            method = "GET",

            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved Product  data",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProductItemResponse.class))),
                    @ApiResponse(responseCode = "4xx", description = "Error retrieving product  data",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDetail.class)))})
    Mono<PageSupport<ProductResponse>> paged(
            @RequestParam(name = "page", defaultValue = FIRST_PAGE_NUM) int page,
            @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size);
//
//
//
//
//

    @Operation(
            summary = "Search Employees ",
            description = "Allow to search employee in Elastic search (with pagination)",
            method = "POST",
            parameters = {
                    @Parameter(name = "page", in = ParameterIn.QUERY, schema = @Schema(type = "integer", defaultValue = "0"),
                            description = "Results page you want to retrieve (0..N)"),
                    @Parameter(name = "size", in = ParameterIn.QUERY, schema = @Schema(type = "integer", defaultValue = "10"),
                            description = "Number of records per page. "),
                    @Parameter(name = "sort", in = ParameterIn.QUERY, schema = @Schema(type = "string", defaultValue = "id,asc"),
                            description = "Sorting criteria in the format: property,asc|dec. " +
                                    "Default sort order is ascending.")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ESSearchFilter.class)),
                    required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully Searched Employee data",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProductResponse.class))),
                    @ApiResponse(responseCode = "4xx", description = "Error retrieving Employee data",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDetail.class)))})
    ResponseEntity<Page<ProductResponse>> searchProducts(@Parameter(hidden = true)
                                                         @PageableDefault(sort = "id", direction = Sort.Direction.ASC)
                                                         @RequestParam(name = "page", defaultValue = FIRST_PAGE_NUM) int page,
                                                         @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size,
                                               @RequestBody ESSearchFilter esSearchFilter);
//
//    @Operation(
//            summary = "Search Employees Multi Match ",
//            description = "Allow to perform multimatch employee  search in Elastic search (with pagination)",
//            method = "POST",
//            parameters = {
//                    @Parameter(name = "page", in = ParameterIn.QUERY, schema = @Schema(type = "integer", defaultValue = "0"),
//                            description = "Results page you want to retrieve (0..N)"),
//                    @Parameter(name = "size", in = ParameterIn.QUERY, schema = @Schema(type = "integer", defaultValue = "10"),
//                            description = "Number of records per page. "),
//                    @Parameter(name = "sort", in = ParameterIn.QUERY, schema = @Schema(type = "string", defaultValue = "id,asc"),
//                            description = "Sorting criteria in the format: property,asc|dec. " +
//                                    "Default sort order is ascending.")
//            },
//            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
//                    schema = @Schema(implementation = String.class)),
//                    required = true),
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "Successfully Searched Employee data",
//                            content = @Content(
//                                    mediaType = "application/json",
//                                    schema = @Schema(implementation = EmployeeDTO.class))),
//                    @ApiResponse(responseCode = "4xx", description = "Error retrieving Employee data",
//                            content = @Content(
//                                    mediaType = "application/json",
//                                    schema = @Schema(implementation = ErrorDetail.class)))})
//    ResponseEntity<Page<EmployeeDTO>> searchEmployeesMultiMatch(@Parameter(hidden = true) @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
//                                                                @RequestBody String searchString);
//

}
