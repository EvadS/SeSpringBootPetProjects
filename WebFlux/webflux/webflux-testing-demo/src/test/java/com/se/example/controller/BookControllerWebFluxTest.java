package com.se.example.controller;

import com.se.example.dao.model.Book;
import com.se.example.dao.repository.BookRepository;
import com.se.example.error.GlobalErrorWebExceptionHandler;
import com.se.example.error.model.GlobalErrorAttributes;
import com.se.example.service.BookService;
import com.se.example.service.impl.BookServiceImpl;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.times;


@Slf4j

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = BookController.class)
@Import({BookServiceImpl.class })
@ContextConfiguration(classes = {
        GlobalErrorWebExceptionHandler.class, GlobalErrorAttributes.class

})
//@ImportAutoConfiguration(ErrorWebFluxAutoConfiguration.class)
//@Tag(TestCase.INTEGRATION)
class BookControllerWebFluxTest {


    @MockBean
    BookRepository repository;

    private BookService bookService;

    @Autowired
    private WebTestClient webClient;

    @BeforeClass
    public  void init(){
        bookService = new BookServiceImpl();
    }

    @Test
    void testCreate() {
        Book book = new Book();
        book.setId(1);
        book.setName("Test");

        Mockito.when(repository.save(book)).thenReturn(Mono.just(book));

        webClient.post()
                .uri("book/book/create")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(book))
                .exchange()
                .expectStatus().isCreated();

        Mockito.verify(repository, times(1)).save(book);
    }

    @Test
    void testGetByName()
    {
        Book book = new Book();
        book.setId(1);
        book.setName("Test");


        List<Book> list = new ArrayList<Book>();
        list.add(book);

        Flux<Book> employeeFlux = Flux.fromIterable(list);

        Mockito
                .when(repository.findByName("Test"))
                .thenReturn(employeeFlux);

        webClient.get().uri("/book/name/{name}", "Test")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Book.class);

        Mockito.verify(repository, times(1)).findByName("Test");
    }

    @Test
    void testGetById()
    {
        Book book = new Book();
        book.setId(12);
        book.setName("Test");

        Mockito
                .when(repository.findById(12))
                .thenReturn(Mono.just(book));

        String uri = "/books/book/12";
        WebTestClient.RequestHeadersSpec<?> wtc = webClient.get().uri(
                uri);

        WebTestClient.BodyContentSpec bodyContentSpec = wtc
                ///"/book/{id}", 100)
                .exchange()
             //   .filter(logRequest())
                .expectStatus().isOk()
                .expectBody();//                .jsonPath("$.name").isNotEmpty()
//                .jsonPath("$.id").isEqualTo(100)
//                .jsonPath("$.name").isEqualTo("Test")

        Mockito.verify(repository, times(1)).findById(100);
    }

    @Test
    void testDeleteEmployee()
    {
        Mono<Void> voidReturn  = Mono.empty();
        Mockito
                .when(repository.deleteById(1))
                .thenReturn(voidReturn);

        webClient.get().uri("/book/delete/{id}", 1)
                .exchange()
                .expectStatus().isOk();
    }
    private ExchangeFilterFunction logRequest() {
        return (clientRequest, next) -> {
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers()
                    .forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
            return next.exchange(clientRequest);
        };
    }

}