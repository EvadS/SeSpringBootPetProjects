package com.se.sample.controller;

import com.se.sample.configuration.CatalogueControllerAPIPaths;
import com.se.sample.exception.FileStorageException;
import com.se.sample.exception.ResourceNotFoundException;
import com.se.sample.model.CatalogueItem;
import com.se.sample.model.ResourceIdentity;
import com.se.sample.model.request.CatalogueRequest;
import com.se.sample.service.CatalogueCrudService;
import com.se.sample.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.Duration;

@Validated
@Slf4j
@RestController
@RequestMapping(CatalogueControllerAPIPaths.BASE_PATH)
public class CatalogueController {


    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private CatalogueCrudService catalogueCrudService;
    /**
     * Get Catalogue Items available in database
     *
     * @return catalogueItems
     */
    @GetMapping(CatalogueControllerAPIPaths.GET_ITEMS)
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<CatalogueItem> getCatalogueItems() {
        return catalogueCrudService.getCatalogueItems();
    }

    /**
     * If api needs to push items as Streams to ensure Backpressure is applied, we need to set produces to MediaType.TEXT_EVENT_STREAM_VALUE
     *
     * MediaType.TEXT_EVENT_STREAM_VALUE  is the official media type for Server Sent Events (SSE)
     * MediaType.APPLICATION_STREAM_JSON_VALUE is for server to server/http client communications.
     *
     * https://stackoverflow.com/questions/52098863/whats-the-difference-between-text-event-stream-and-application-streamjson
     * @return catalogueItems
     */
    @GetMapping(path= CatalogueControllerAPIPaths.GET_ITEMS_STREAM, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<CatalogueItem> getCatalogueItemsStream() {
        return catalogueCrudService
                .getCatalogueItems()
                .delayElements(Duration.ofMillis(200));
    }

    /**
     * Get Catalogue Item by SKU
     * @param skuNumber
     * @return catalogueItem
     * @throws ResourceNotFoundException
     */
    @GetMapping(CatalogueControllerAPIPaths.GET_ITEM)
    public Mono<CatalogueItem>
    getCatalogueItemBySKU(@PathVariable(value = "sku") String skuNumber)
            throws ResourceNotFoundException {

        return catalogueCrudService.getCatalogueItem(skuNumber);
    }

    /**
     * Create Catalogue Item
     * @param catalogueItem
     * @return id of created CatalogueItem
     */
    @PostMapping(CatalogueControllerAPIPaths.CREATE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<ResponseEntity> addCatalogueItem(@Valid @RequestBody CatalogueRequest catalogueItem) {

        Mono<Long> id = catalogueCrudService.addCatalogItem(catalogueItem);

        return id.map(value -> ResponseEntity.status(HttpStatus.CREATED).body(new ResourceIdentity(value))).cast(ResponseEntity.class);
    }

    /**
     * Update Catalogue Item by SKU
     * @param skuNumber
     * @param catalogueItem
     * @throws ResourceNotFoundException
     */
    @PutMapping(CatalogueControllerAPIPaths.UPDATE)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateCatalogueItem(
            @PathVariable(value = "sku") String skuNumber,
            @Valid @RequestBody CatalogueItem catalogueItem) throws ResourceNotFoundException {

        catalogueCrudService.updateCatalogueItem(catalogueItem);
    }

    /**
     * Delete Catalogue Item by SKU
     * @param skuNumber
     * @throws ResourceNotFoundException
     */
    @DeleteMapping(CatalogueControllerAPIPaths.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeCatalogItem(@PathVariable(value = "sku") String skuNumber)
            throws ResourceNotFoundException {

        Mono<CatalogueItem> catalogueItem = catalogueCrudService.getCatalogueItem(skuNumber);
        catalogueItem.subscribe(
                value -> {
                    catalogueCrudService.deleteCatalogueItem(value);
                }
        );
    }

    /**
     * Upload image to the Catalogue Item by SKU
     * @param skuNumber
     * @param filePart
     * @throws ResourceNotFoundException
     * @throws FileStorageException
     */
    @PostMapping(CatalogueControllerAPIPaths.UPLOAD_IMAGE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void uploadCatalogueItemImage(
            @PathVariable(value = "sku") String skuNumber,
            @RequestPart("file") FilePart filePart)
            throws ResourceNotFoundException {

        Mono<CatalogueItem> catalogueItem = catalogueCrudService.getCatalogueItem(skuNumber);
        catalogueItem.subscribe(
                value -> {
                    fileStorageService
                            .storeFile(filePart)
                            .subscribe();
                }
        );
    }
}