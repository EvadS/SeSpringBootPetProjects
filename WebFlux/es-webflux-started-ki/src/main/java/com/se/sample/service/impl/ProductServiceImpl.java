package com.se.sample.service.impl;

import com.se.sample.entity.dto.ProductResponse;
import com.se.sample.entity.dto.response.ProductItemResponse;
import com.se.sample.entity.dto.response.ProductRequest;
import com.se.sample.entity.mapper.ProductMapper;
import com.se.sample.entity.model.Product;
import com.se.sample.helper.PageSupport;
import com.se.sample.repository.ProductRepository;
import com.se.sample.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

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



//    public Flux<User> fetchUsers(List<Integer> userIds) {
//        return Flux.fromIterable(userIds)
//                .parallel()
//                .runOn(Schedulers.elastic())
//                .flatMap(i -> findById(i))
//                .ordered((u1, u2) -> u2.getId() - u1.getId());
//    }
}
