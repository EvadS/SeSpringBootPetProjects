package com.se.sample.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.entity.Product;
import com.se.sample.helper.FilterBuilderHelper;
import com.se.sample.helper.PageSupport;
import com.se.sample.models.filter.ESSearchFilter;
import com.se.sample.models.mapper.ProductMapper;
import com.se.sample.models.request.ProductItemResponse;
import com.se.sample.models.request.ProductRequest;
import com.se.sample.models.response.ProductResponse;
import com.se.sample.repository.ProductRepository;
import com.se.sample.service.ProductService;
import lombok.AllArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    //private ElasticsearchOperations elasticsearchRestTemplate;

    private final ObjectMapper objectMapper;

    @Autowired
    RestHighLevelClient elasticsearchRestTemplate;

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
    public Mono<ProductResponse> update(final String id, final Product product) {
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


                });
    }

    @Override
    public Mono save(final ProductRequest productRequest) {
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
    public Page<ProductResponse> searchProductByCriteria(ESSearchFilter esSearchFilter,
                                                         org.springframework.data.domain.Pageable pageable) {


        QueryBuilder query = FilterBuilderHelper.build(esSearchFilter);
        NativeSearchQuery nativeSearchQuery = null;

        nativeSearchQuery = new NativeSearchQueryBuilder().withPageable(
                pageable).withQuery(query).build();

        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder sourceBuilder = SearchSourceBuilder.searchSource();

        sourceBuilder.query(nativeSearchQuery.getQuery());

        searchRequest.source(sourceBuilder);

        try {
            SearchResponse response = elasticsearchRestTemplate.search(searchRequest, RequestOptions.DEFAULT);

            List<ProductResponse> collect = Arrays.stream(response.getHits().getHits()).map(
                    i -> this.convert(i)).collect(Collectors.toList());

            toProductList(response.getHits().getHits()).stream().findFirst().orElse(null);

//            List<String> names = Arrays.stream(response.getHits().getHits())
//                    .map(h -> h.().get(FooIndexDefinition.FIELD_NAME).toString())
//                    .collect(MoreCollectors.toList());


            int a = 0;
        } catch (Exception   e) {

            int b = 0;
            e.printStackTrace();
        }

        return null;


//        QueryBuilder query = FilterBuilderHelper.build(esSearchFilter);
//        NativeSearchQuery nativeSearchQuery = null;
//        if (null != pageable) {
//            nativeSearchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(query).build();
//        } else {
//            nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(query).build();
//        }
//        return employeeESRepo.search(nativeSearchQuery);

    }

    private List<Product> toProductList(SearchHit[] searchHits) throws Exception {
        List<Product> productList = new ArrayList<>();
        for (SearchHit searchHit : searchHits) {
            productList.add(objectMapper.readValue(searchHit.getSourceAsString(), Product.class));
        }
        return productList;
    }

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
