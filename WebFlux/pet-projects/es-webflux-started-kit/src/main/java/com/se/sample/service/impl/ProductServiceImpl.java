package com.se.sample.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.ProductValidator;
import com.se.sample.entity.Product;
import com.se.sample.errors.exception.AlreadyExistException;
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
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
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
import reactor.core.publisher.MonoSink;

import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final ProductRepository productRepository;
    private final ProductValidator productValidator;

    @Autowired
    private final ElasticsearchOperations elasticsearchOperations;
    @Autowired
    RestHighLevelClient client;

    @Override
    public Flux<ProductItemResponse> getAll() {
        return productRepository.findAll()
                .map(ProductMapper.INSTANCE::toProductItemResponse)
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<ProductResponse> getById(final String id) {
        Mono<ProductResponse> product = productRepository.findById(id)
                .switchIfEmpty(Mono.error(
                        new ResourceNotFoundException("Product", "id", id)))
                .map(ProductMapper.INSTANCE::toProductResponse);

        return product;
    }

    @Override
    public Mono<ProductResponse> update(final String id, final ProductRequest product) {

        Mono<ProductResponse> error = Mono.error(new ResourceNotFoundException("Product", "id", id));
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
                .switchIfEmpty(error);
    }

    @Override
    public Mono<ProductResponse> save(final ProductRequest productRequest) {

        //TODO Validation: for learning --->
        List<String> errors = productValidator.validateEmployee(productRequest);
        if (!CollectionUtils.isEmpty(errors)) {
            errors.stream().forEach(i ->
            {
                // TODO: throw constraint validation exception
                System.out.println("****" + i);
            });
            //throw new InvalidResourceException(errors);
        }
        // <-- for learning

        return productRepository.existsByName(productRequest.getName())
                .flatMap(item -> {
                    if (item.booleanValue()) {
                        log.debug("Product already exists. Request: {}", productRequest);
                        throw new AlreadyExistException("Product", "name", productRequest.getName());

                    } else {
                        Product product = ProductMapper.INSTANCE.toProduct(productRequest);
                        Mono<Product> savedProduct = productRepository.save(product);

                        savedProduct.subscribe(sp -> {
                            log.info("Product saved, id: {}, name: {}", sp.getId(), sp.getName());
                        });

                        return savedProduct.map(ProductMapper.INSTANCE::toProductResponse);
                    }
                });
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
    public Mono<Product> search(SearchRequestDTO dto) {

//        final SearchRequest request = SearchUtil.buildSearchRequest(
//                Indices.PERSON_INDEX,
//                dto
//        );
//
//        return searchInternal(request);
        ObjectMapper objectMapper = new ObjectMapper();

        return Mono.<GetResponse>create(sink ->
                client.getAsync(new GetRequest(Indices.PERSON_INDEX, "MkNvDXwBt7Mcg3CaGiB0"),
                        RequestOptions.DEFAULT, listenerToSink(sink))
        )
                // .filter(GetResponse::isExists)
                //.map(GetResponse::getSource)
                .map(i -> {

                    Map<String, Object> r = i.getSource();
                    return r;
                })

                .map(map -> objectMapper.convertValue(map, Product.class));
    }

    private <T> ActionListener<T> listenerToSink(MonoSink<T> sink) {
        return new ActionListener<T>() {
            @Override
            public void onResponse(T response) {
                sink.success(response);
            }

            @Override
            public void onFailure(Exception e) {
                sink.error(e);
            }
        };
    }

    //
    private List<Product> searchInternal(final SearchRequest request) {
        if (request == null) {
            log.error("Failed to build search request");
            return Collections.emptyList();
        }

        try {
            final SearchResponse response = client.search(request, RequestOptions.DEFAULT);

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
