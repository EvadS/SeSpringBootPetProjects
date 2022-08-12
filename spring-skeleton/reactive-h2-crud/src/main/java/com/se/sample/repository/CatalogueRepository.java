package com.se.sample.repository;


import com.se.sample.model.CatalogueItem;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CatalogueRepository extends ReactiveSortingRepository<CatalogueItem, Long> {

    Mono<CatalogueItem> findBySku(String sku);
    Flux<CatalogueItem> findAll(Sort sort);
}