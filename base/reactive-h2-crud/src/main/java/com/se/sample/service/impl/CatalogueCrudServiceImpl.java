package com.se.sample.service.impl;

import com.se.sample.event.CatalogueItemEvent;
import com.se.sample.exception.ResourceNotFoundException;
import com.se.sample.mapper.CatalogMapper;
import com.se.sample.model.CatalogueItem;
import com.se.sample.model.request.CatalogueRequest;
import com.se.sample.repository.CatalogueRepository;
import com.se.sample.service.CatalogueCrudService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.data.domain.Sort;
import java.time.Instant;
import java.util.List;

@Slf4j
@Service
//@AllArgsConstructor
public class CatalogueCrudServiceImpl implements CatalogueCrudService {


    private final ApplicationEventPublisher publisher;
    private final CatalogueRepository catalogueRepository;


    public CatalogueCrudServiceImpl(ApplicationEventPublisher publisher, CatalogueRepository catalogueRepository) {
        this.publisher = publisher;
        this.catalogueRepository = catalogueRepository;
    }


    @Override
    public Mono<Long> addCatalogItem(CatalogueRequest catalogRequest) {

        CatalogueItem catalogueItem = CatalogMapper.INSTANCE.toCatalogueItem(catalogRequest);

        return
                catalogueRepository
                        .save(catalogueItem)
                        .doOnSuccess(item -> publishCatalogueItemEvent(CatalogueItemEvent.CATALOGUEITEM_CREATED, item))
                        .flatMap(item -> Mono.just(item.getId()));
    }

    @Override
    public Flux<CatalogueItem> getCatalogueItems() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");

        Flux<CatalogueItem> all = catalogueRepository.findAll(sort);

        all.subscribe(i-> log.info(i.toString()));
        return all;
    }

    @Override
    public Mono<CatalogueItem> getCatalogueItem(String skuNumber) throws ResourceNotFoundException {
        return getCatalogueItemBySku(skuNumber);
    }

    @Override
    public void updateCatalogueItem(CatalogueItem catalogueItem) throws ResourceNotFoundException{

        Mono<CatalogueItem> catalogueItemfromDB = getCatalogueItemBySku(catalogueItem.getSku());

        catalogueItemfromDB.subscribe(
                value -> {
                    value.setName(catalogueItem.getName());
                    value.setDescription(catalogueItem.getDescription());
                    value.setPrice(catalogueItem.getPrice());
                    value.setInventory(catalogueItem.getInventory());
                    value.setUpdatedOn(Instant.now());

                    catalogueRepository
                            .save(value)
                            .doOnSuccess(item -> publishCatalogueItemEvent(CatalogueItemEvent.CATALOGUEITEM_UPDATED, item))
                            .subscribe();
                });
    }

    @Override
    public void deleteCatalogueItem(CatalogueItem catalogueItem) {

        // For delete to work as expected, we need to subscribe() for the flow to complete
        catalogueRepository.delete(catalogueItem).subscribe();
    }

    private Mono<CatalogueItem> getCatalogueItemBySku(String skuNumber) throws ResourceNotFoundException {
        return catalogueRepository.findBySku(skuNumber)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new ResourceNotFoundException(
                        String.format("Catalogue Item not found for the provided SKU :: %s" , skuNumber)))));
    }

    private final void publishCatalogueItemEvent(String eventType, CatalogueItem item) {
        this.publisher.publishEvent(new CatalogueItemEvent(eventType, item));
    }
}
