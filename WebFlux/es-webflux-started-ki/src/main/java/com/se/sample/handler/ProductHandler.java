package com.se.sample.handler;



import com.se.sample.models.request.ProductRequest;
import com.se.sample.entity.model.Product;
import com.se.sample.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class ProductHandler {

    private final ProductService productService;

    public ProductHandler(ProductService personService) {
        this.productService = personService;
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        String id = request.pathVariable("id");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.getById(id), Product.class);
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.getAll(), Product.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        final Mono<ProductRequest> person = request.bodyToMono(ProductRequest.class);
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(
                        person.flatMap(productService::save), Product.class));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.delete(id), Void.class);
    }

}