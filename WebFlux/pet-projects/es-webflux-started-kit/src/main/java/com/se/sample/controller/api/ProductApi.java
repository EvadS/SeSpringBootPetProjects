package com.se.sample.controller.api;


import com.se.sample.constants.GeneralConstants;
import com.se.sample.errors.model.ErrorDetail;
import com.se.sample.helper.PageSupport;
import com.se.sample.models.filter.ESSearchFilter;
import com.se.sample.models.request.ProductItemResponse;
import com.se.sample.models.request.ProductRequest;
import com.se.sample.models.response.ProductResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

import static com.se.sample.helper.PageSupport.DEFAULT_PAGE_SIZE;
import static com.se.sample.helper.PageSupport.FIRST_PAGE_NUM;

@OpenAPIDefinition(info = @Info(title = "Product API",
        version = "1.0", description = "Product Information"))
public interface ProductApi {
    // get by id block -->
    @Operation(
            summary = "Get Product By Id",
            description = "Allow to get Product by Id",
            method = "GET",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved Product data",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProductResponse.class))),
                    @ApiResponse(responseCode = "404",
                            description = GeneralConstants.NOT_FOUND,
                            content = @Content(schema = @Schema(implementation = ErrorDetail.class))),
                    @ApiResponse(responseCode = "415",
                            description = GeneralConstants.INCORRECT_MEDIA_TYPE,
                            content = @Content(schema = @Schema(implementation = ErrorDetail.class))),
                    @ApiResponse(responseCode = "500",
                            description = GeneralConstants.HTTP_INTERNAL_ERROR,
                            content = @Content(schema = @Schema(implementation = ErrorDetail.class))),
            })
    public Mono<ResponseEntity<ProductResponse>> getOne(
            @Parameter(description = "unique identifier to be searched")
            @PathVariable(name = "id") final String id);
    // <<-- get by id block


    // Create item block -->
    @Operation(
            summary = "Create Product",
            description = "Allow to save Products",
            method = "POST",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductRequest.class)),
                    required = true),
            responses = {
                    @ApiResponse(responseCode = "201", description = GeneralConstants.HTTP_OK_CREATED,
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProductResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = GeneralConstants.HTTP_BAD_REQUEST,
                            content = @Content(schema = @Schema(implementation = ErrorDetail.class))),
                    @ApiResponse(
                            responseCode = "409",
                            description = GeneralConstants.HTTP_ALREADY_EXISTS,
                            content = @Content(schema = @Schema(implementation = ErrorDetail.class))),
                    @ApiResponse(
                            responseCode = "412",
                            description = GeneralConstants.HTTP_BAD_REQUEST,
                            content = @Content(schema = @Schema(implementation = ErrorDetail.class))),
                    @ApiResponse(
                            responseCode = "415",
                            description = GeneralConstants.INCORRECT_MEDIA_TYPE,
                            content = @Content(schema = @Schema(implementation = ErrorDetail.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = GeneralConstants.VALIDATION_ERROR,
                            content = @Content(schema = @Schema(implementation = ErrorDetail.class)))
            })
    public Mono<ResponseEntity<ProductResponse>> save(
            @Valid @RequestBody final ProductRequest productRequest);
    //<<-- Create item block


    // update item block -->
    @Operation(
            summary = "Update Product",
            description = "Allow to update Products",
            method = "PUT",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductRequest.class)),
                    required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated Product data",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProductResponse.class))),

                    @ApiResponse(responseCode = "400", description = GeneralConstants.HTTP_BAD_REQUEST,
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDetail.class))),

                    @ApiResponse(responseCode = "404", description = GeneralConstants.HTTP_NOT_FOUND,
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDetail.class))),

                    @ApiResponse(responseCode = "412", description = GeneralConstants.INCORRECT_REQUEST_PARAM,
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDetail.class))),

                    @ApiResponse(responseCode = "415", description = GeneralConstants.INCORRECT_MEDIA_TYPE,
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDetail.class))),

                    @ApiResponse(responseCode = "422", description = GeneralConstants.VALIDATION_ERROR,
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDetail.class)))
            })
    public Mono<ResponseEntity<ProductResponse>> update(
            @Parameter(name = "id", description = "unique identifier",
                    in = ParameterIn.PATH, required = true)
            @PathVariable("id") final String id,
            @Valid @RequestBody final ProductRequest productRequest);
    //<<-- update item block

    // delete by is block -->
    @Operation(
            summary = "Delete Product By Id",
            description = "Allow to delete Product by Id",
            method = "DELETE",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully deleted Product data",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Void.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = GeneralConstants.NOT_FOUND,
                            content = @Content(schema = @Schema(implementation = ErrorDetail.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = GeneralConstants.HTTP_INTERNAL_ERROR,
                            content = @Content(schema = @Schema(implementation = ErrorDetail.class))),

            })
    public Mono<ResponseEntity<Void>> deleteById(
            @Parameter(name = "id", description = "unique identifier",
                    in = ParameterIn.PATH, required = true)
            @PathVariable(name = "id") final String id);
    // <<-- delete by is block

    // get all block -->
    @Operation(
            summary = "Get All",
            description = "Allow to get all products",
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
    public Mono<ResponseEntity<List<ProductItemResponse>>> getAll();
    //<<-- get all block

    @Operation(
            summary = "paged All",
            description = "Allow to get paged products",
            method = "GET",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved Products data", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductItemResponse.class)))}),

                    @ApiResponse(responseCode = "4xx", description = "Error retrieving product  data",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDetail.class)))})
    public Mono<ResponseEntity<PageSupport<ProductResponse>>> paged(
            @Parameter(name = "page", description = "zero base page index",
                    in = ParameterIn.PATH, required = true)
            @RequestParam(name = "page", defaultValue = FIRST_PAGE_NUM) int page,

            @Parameter(name = "size", description = "the size of the page to be returned",
                    in = ParameterIn.PATH, required = true)
            @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size);


    //TODO: doesn't work
    @Operation(
            summary = "Search products",
            description = "Allow to search products in Elastic search (with pagination)",
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
                    @ApiResponse(responseCode = "200", description = "Successfully Searched Product data",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProductResponse.class))),
                    @ApiResponse(responseCode = "4xx", description = "Error retrieving Product data",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDetail.class)))})
    public ResponseEntity<Page<ProductResponse>> search(@Parameter(hidden = true)
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
