package com.se.sample.controller;

import com.se.sample.exception.ErrorResponse;
import com.se.sample.exception.FileStorageException;
import com.se.sample.exception.ResourceNotFoundException;
import com.se.sample.model.CatalogueItem;
import com.se.sample.model.CatalogueItemList;
import com.se.sample.model.ResourceIdentity;
import com.se.sample.service.CatalogueCrudService;
import com.se.sample.service.FileStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping(CatalogueControllerAPIPaths.BASE_PATH)
public class CatalogueController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private CatalogueCrudService catalogueCrudService;

    @GetMapping(CatalogueControllerAPIPaths.GET_ITEMS)
    @ResponseStatus(value = HttpStatus.OK)
    public CatalogueItemList getCatalogueItems() {
        return new CatalogueItemList(catalogueCrudService.getCatalogueItems());
    }

    @Operation(summary = "catalogs", description = "get all catalog", tags = {"catalogs"})

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "get all ",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CatalogueItem.class)))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "bad request ",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @GetMapping(CatalogueControllerAPIPaths.GET_ITEM)
    public CatalogueItem
    getCatalogueItemBySKU(@PathVariable(value = "sku") String skuNumber)
            throws ResourceNotFoundException {

        return catalogueCrudService.getCatalogueItem(skuNumber);
    }

    @PostMapping(CatalogueControllerAPIPaths.CREATE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResourceIdentity> addCatalogueItem(@Valid @RequestBody CatalogueItem catalogueItem) {

        Long id = catalogueCrudService.addCatalogItem(catalogueItem);

        return new ResponseEntity<>(new ResourceIdentity(id), HttpStatus.CREATED);
    }

    @PutMapping(CatalogueControllerAPIPaths.UPDATE)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateCatalogueItem(
            @PathVariable(value = "sku") String skuNumber,
            @Valid @RequestBody CatalogueItem catalogueItem) throws ResourceNotFoundException {

        catalogueCrudService.updateCatalogueItem(catalogueItem);
    }

    @DeleteMapping(CatalogueControllerAPIPaths.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeCatalogItem(@PathVariable(value = "sku") String skuNumber)
            throws ResourceNotFoundException {

        catalogueCrudService.deleteCatalogueItem(catalogueCrudService.getCatalogueItem(skuNumber));
    }

    @PostMapping(CatalogueControllerAPIPaths.UPLOAD_IMAGE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void uploadCatalogueItemImage(
            @PathVariable(value = "sku") String skuNumber,
            @RequestParam("file") MultipartFile file)
            throws ResourceNotFoundException, FileStorageException {

        catalogueCrudService.getCatalogueItem(skuNumber);

        fileStorageService.storeFile(file);
    }
}
