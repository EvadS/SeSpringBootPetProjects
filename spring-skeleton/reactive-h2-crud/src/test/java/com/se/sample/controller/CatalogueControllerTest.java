package com.se.sample.controller;

import com.se.sample.CatalogueItemGenerator;
import com.se.sample.ReactiveH2CrudApplication;
import com.se.sample.model.CatalogueItem;
import com.se.sample.model.request.CatalogueRequest;
import com.se.sample.service.CatalogueCrudService;
import com.se.sample.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.se.sample.configuration.CatalogueControllerAPIPaths.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest(
        classes = ReactiveH2CrudApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CatalogueControllerTest {

    private static WebTestClient client;
    private static final CatalogueItem catalogueItem = CatalogueItemGenerator.generateCatalogueItem();

    @LocalServerPort
    int port;

    @MockBean
    private FileStorageService fileStorageService;

    @Autowired
    private CatalogueCrudService catalogueCrudService;

    @Autowired
    public void setApplicationContext(ApplicationContext context) {
        client
                = WebTestClient
                .bindToApplicationContext(context)
                .configureClient()
                .baseUrl(BASE_PATH)
                .build();
    }

    @Test
    @Order(10)
    void testGetAllCatalogueItems() {

        client
                .get()
                .uri(GET_ITEMS)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.[0].id").isNotEmpty()
                .jsonPath("$.[0].sku").isNotEmpty()
                .jsonPath("$.[0].name").isNotEmpty()
                .jsonPath("$.[0].description").isNotEmpty();
    }

    @Test
    @Order(20)
    void testGetCatalogueItem() throws Exception {

        createCatalogueItem();

        client
                .get()
                .uri(replaceSKU(GET_ITEM))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.sku").isNotEmpty()
                .jsonPath("$.name").isNotEmpty()
                .jsonPath("$.description").isNotEmpty();
    }

    @Test
    @Order(30)
    void testGetCatalogueItemsStream() throws Exception {

        FluxExchangeResult<CatalogueItem> result
                = client
                .get()
                .uri(GET_ITEMS_STREAM)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk()
                .returnResult(CatalogueItem.class);

        Flux<CatalogueItem> events = result.getResponseBody();
        StepVerifier
                .create(events)
                .expectNextMatches(catalogueItem -> catalogueItem.getId() == 1l)
                .expectNextMatches(catalogueItem -> catalogueItem.getId() == 2l)
                .expectNextMatches(catalogueItem -> catalogueItem.getId() == 3l)
                .thenCancel()
                .verify();
    }

    @Test
    @Order(40)
    void testCreateCatalogueItem() {
        CatalogueItem item = CatalogueItemGenerator.generateCatalogueItem();
        item.setId(null);

        client
                .post()
                .uri(CREATE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(item), CatalogueItem.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @Order(40)
    void testUpdateCatalogueItem() throws Exception {
        createCatalogueItem();

        client
                .put()
                .uri(replaceSKU(UPDATE))
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(CatalogueItemGenerator.generateCatalogueItem()), CatalogueItem.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @Order(50)
    void testDeleteCatalogueItem() throws Exception {
        createCatalogueItem();

        client
                .delete()
                .uri(replaceSKU(DELETE))
                .exchange()
                .expectStatus().isNoContent();
    }

    /**
     * Test method to validate create catalogue item if Invalid Category is passed in request
     */
    @Test
    @Order(60)
    void testCreateCatalogueItemWithInvalidCategory() {

        CatalogueRequest catalogueItem = CatalogueItemGenerator.generateCatalogueRequest();
        catalogueItem.setCategory("INVALID");


        catalogueItem.setCategory("");
        client
                .post()
                .uri(CREATE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(catalogueItem), CatalogueItem.class)
                .exchange()
                .expectStatus().isBadRequest()
                .expectHeader().contentType(MediaType.APPLICATION_JSON);
    }

    /**
     * Test method to validate Resource not found exception
     */
    @Test
    @Order(70)
    void testResourceNotFoundException() throws Exception {

        client
                .get()
                .uri(GET_ITEM.replaceAll("\\{sku\\}", "INVALID"))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectHeader().contentType(MediaType.APPLICATION_JSON);
    }

    /**
     * Test CatalogueItem Image file upload
     *
     * @throws Exception
     */
    @Test
    @Order(80)
    void testCatalogueItemImageUpload() throws Exception {

        when(fileStorageService.storeFile(any())).thenReturn(Mono.just("FILE_NAME"));

        createCatalogueItem();

        MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
        multipartBodyBuilder
                .part("file", new ClassPathResource("application.yml"));

        client
                .post()
                .uri(replaceSKU(UPLOAD_IMAGE))
                .body(BodyInserters.fromMultipartData(multipartBodyBuilder.build()))
                .exchange()
                .expectStatus().isCreated();
    }

    private void createCatalogueItem() {
        CatalogueItem item = CatalogueItemGenerator.generateCatalogueItem();
        item.setId(null);

        client
                .post()
                .uri(CREATE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(item), CatalogueItem.class)
                .exchange()
                .expectStatus().isCreated();
    }

    private String replaceSKU(String path) {
        return path.replaceAll("\\{sku\\}", catalogueItem.getSku());
    }
}