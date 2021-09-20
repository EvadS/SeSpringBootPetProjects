package com.se.sample.router;


import com.se.sample.handler.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class PersonRouter {

    @Bean
    public RouterFunction<ServerResponse> route(ProductHandler handler) {
        return RouterFunctions
                .route(GET("/gets").and(accept(MediaType.APPLICATION_JSON)), handler::findAll)
                .andRoute(GET("/get/{id}").and(accept(MediaType.APPLICATION_STREAM_JSON)), handler::findById)
                .andRoute(POST("/create").and(accept(MediaType.APPLICATION_JSON)), handler::save)
                .andRoute(DELETE("/delete/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::delete);
    }

}
