package com.se.sample.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.ProductValidator;
import com.se.sample.entity.Product;
import com.se.sample.errors.exception.ResourceNotFoundException;
import com.se.sample.helper.Indices;
import com.se.sample.helper.PageSupport;
import com.se.sample.models.SearchRequestDTO;
import com.se.sample.models.mapper.ProductMapper;
import com.se.sample.models.request.ProductItemResponse;
import com.se.sample.models.request.ProductRequest;
import com.se.sample.models.response.ProductResponse;
import com.se.sample.repository.ProductRepository;
import com.se.sample.service.ProductService;
import com.se.sample.util.SearchUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    ;
    private final ProductRepository productRepository;


//    private final ObjectMapper objectMapper;

    private ProductValidator productValidator;

    @Autowired
    RestHighLevelClient elasticsearchRestTemplate;

//    private final RestHighLevelClient client;

    @Override
    public Flux<ProductItemResponse> getAll() {
        return productRepository.findAll()
                .map(ProductMapper.INSTANCE::toProductItemResponse)
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<ProductResponse> getById(final String id) {
        Mono<ProductResponse> product = productRepository.findById(id)
                .map(ProductMapper.INSTANCE::toProductResponse);


        return product;
    }

    @Override
    public Mono<ProductResponse> update(final String id, final ProductRequest product) {
//        return productRepository.save(product);


        return productRepository.findById(id)
                .flatMap(item -> {
                    item.setCategory(product.getCategory());
                    item.setDescription(product.getDescription());
                    item.setPrice(product.getPrice());
                    item.setName(product.getName());
                    return productRepository.save(item)
                            .map(i -> {
                                return ProductMapper.INSTANCE.toProductResponse(i);
                            });


                })
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("product", "id", id)));


        //   bucketRepository.findById(bucketId)
////                .map(saveBucket -> ResponseEntity.ok(saveBucket))
////                .defaultIfEmpty(ResponseEntity.notFound().build());
////            .switchIfEmpty(Mono.error(new ResourceNotFoundException("Data not found")));
    }

    @Override
    public Mono save(final ProductRequest productRequest) {

        //TODO Validation: for learning --->
        List<String> errors = productValidator.validateEmployee(productRequest);
        if (!CollectionUtils.isEmpty(errors)) {
            errors.stream().forEach(i->
            {
                System.out.println("****" + i.toString());
            });
            //throw new InvalidResourceException(errors);
        }
        // <-- for learning


        Product product = ProductMapper.INSTANCE.toProduct(productRequest);
        return productRepository.save(product);
    }

    @Override
    public Mono delete(final String id) {
        final Mono<Product> dbPerson = productRepository.findById(id);
        if (Objects.isNull(dbPerson)) {
            return Mono.empty();
        }
        return productRepository.findById(id)
                .switchIfEmpty(Mono.empty()).filter(Objects::nonNull)
                .flatMap(productToBeDeleted -> productRepository
                        .delete(productToBeDeleted).then(Mono.just(productToBeDeleted)));
    }

    @Override
    public Mono<PageSupport<ProductResponse>> getPageResponse(PageRequest page) {
        // TODO: get all records from data base. Not correct
        Mono<PageSupport<ProductResponse>> map = productRepository.findAll()
                .collectList()
                .map(list -> new PageSupport<>(
                        list
                                .stream()
                                .map(ProductMapper.INSTANCE::toProductResponse)
                                .skip(page.getPageNumber() * page.getPageSize())
                                .limit(page.getPageSize())
                                .collect(Collectors.toList()),
                        page.getPageNumber(), page.getPageSize(), list.size()));

        return map;
    }

    @Override
    public Page<ProductResponse> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Product> search(SearchRequestDTO dto) {
        final SearchRequest request = SearchUtil.buildSearchRequest(
                Indices.PERSON_INDEX,
                dto
        );

        return searchInternal(request);

    }

    //
    private List<Product> searchInternal(final SearchRequest request) {
        if (request == null) {
            log.error("Failed to build search request");
            return Collections.emptyList();
        }

        try {
            final SearchResponse response = elasticsearchRestTemplate.search(request, RequestOptions.DEFAULT);

            final SearchHit[] searchHits = response.getHits().getHits();
            final List<Product> vehicles = new ArrayList<>(searchHits.length);
            for (SearchHit hit : searchHits) {
                Product e = MAPPER.readValue(hit.getSourceAsString(), Product.class);
                e.setId(hit.getId());
                vehicles.add(
                        e
                );
            }

            return vehicles;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;


//    private List<Product> toProductList(SearchHit[] searchHits) throws Exception {
//        List<Product> productList = new ArrayList<>();
//        for (SearchHit searchHit : searchHits) {
//            productList.add(objectMapper.readValue(searchHit.getSourceAsString(), Product.class));
//        }
//        return productList;
//    }

    private ProductResponse convert(SearchHit i) {

        return new ProductResponse();
    }


//    public Flux<User> fetchUsers(List<Integer> userIds) {
//        return Flux.fromIterable(userIds)
//                .parallel()
//                .runOn(Schedulers.elastic())
//                .flatMap(i -> findById(i))
//                .ordered((u1, u2) -> u2.getId() - u1.getId());
//    }
}
