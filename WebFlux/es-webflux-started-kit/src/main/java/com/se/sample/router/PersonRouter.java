package com.se.sample.router;


import com.se.sample.handler.ProductHandler;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class PersonRouter {

//    @Bean
//    @RouterOperation(operation = @Operation(operationId = "findEmployeeById", summary = "Find purchase order by ID", tags = { "MyEmployee" },
//            parameters = { @Parameter(in = ParameterIn.PATH, name = "id", description = "Employee Id") },
//            responses = { @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Employee.class))),
//                    @ApiResponse(responseCode = "400", description = "Invalid Employee ID supplied"),
//                    @ApiResponse(responseCode = "404", description = "Employee not found") }))
    public RouterFunction<ServerResponse> route(ProductHandler handler) {
        return RouterFunctions

                .route(GET("/gets").and(accept(MediaType.APPLICATION_JSON)), handler::findAll)
                .andRoute(GET("/get/{id}").and(accept(MediaType.APPLICATION_STREAM_JSON)), handler::findById)
                .andRoute(POST("/create").and(accept(MediaType.APPLICATION_JSON)), handler::save)
                .andRoute(DELETE("/delete/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::delete);
    }

}
