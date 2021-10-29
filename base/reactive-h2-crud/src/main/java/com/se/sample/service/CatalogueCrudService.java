package com.se.sample.service;

import com.se.sample.exception.ResourceNotFoundException;
import com.se.sample.model.CatalogueItem;
import com.se.sample.model.request.CatalogueRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CatalogueCrudService {
     Mono<Long> addCatalogItem(CatalogueRequest catalogueItem) ;

     Flux<CatalogueItem> getCatalogueItems() ;

     Mono<CatalogueItem> getCatalogueItem(String skuNumber) throws ResourceNotFoundException;

     void updateCatalogueItem(CatalogueItem catalogueItem) throws ResourceNotFoundException;

     void deleteCatalogueItem(CatalogueItem value);
}
