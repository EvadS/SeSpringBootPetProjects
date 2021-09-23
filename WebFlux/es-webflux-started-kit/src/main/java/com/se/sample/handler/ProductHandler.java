package com.se.sample.handler;



import com.se.sample.entity.Product;
import com.se.sample.models.mapper.ProductMapper;
import com.se.sample.models.request.ProductRequest;

import com.se.sample.repository.ProductRepository;
import com.se.sample.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import com.se.sample.errors.exception.ResourceNotFoundException;
import javax.validation.Validator;

@Component
public class ProductHandler {

    private final ProductService productService;
    private final Validator validator;
    private final RequestHandler requestHandler;

    // TODO: for test handling
    private ProductRepository productRepository;


    public ProductHandler(ProductService personService, Validator validator, RequestHandler requestHandler) {
        this.productService = personService;
        this.validator = validator;
        this.requestHandler = requestHandler;
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        // TODO: for test handling

        String id = request.pathVariable("id");
        productRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("user","id",id)));



        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.getById(id), Product.class);
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.getAll(), Product.class);
    }

    //http://localhost:8080/create
    public Mono<ServerResponse> save(ServerRequest request) {
        final Mono<ProductRequest> person = request.bodyToMono(ProductRequest.class);
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(
                        person.flatMap(productService::save), Product.class));

/*
FlatMap аналогичен map, за исключением того, что он как-бы “распаковывает” возвращаемое значение лямбда-выражения,
если само значение содержится в Publisher<T>.
 */

//        return requestHandler.requireValidBody(body -> {
////            Mono<Book> bookMono = body.map(addBook -> Book.of(addBook.getTitle(),
////                    addBook.getAuthor()));
//
//            Mono<ProductRequest> bookMono = request.bodyToMono(ProductRequest.class);
//
//            return fromPublisher(bookMono.flatMap(oductService.save())bookMono)
//                    //.map(Book::getRef)
//                 //   .map(ref -> URI.create("http://localhost:8080/books/" + ref))
//                    .map(ServerResponse::created)
//                    .flatMap(ServerResponse.HeadersBuilder::build)
//                    .next();
//        }, request, Product.class);
    }

    //https://medium.com/@samuelsko/different-ways-to-validate-requests-spring-webflux-routerfunctions-6427c331d224
    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.delete(id), Void.class);
    }

}