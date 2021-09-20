package com.se.sample.service.impl;

import com.se.sample.entity.dto.ProductResponse;
import com.se.sample.entity.mapper.ProductMapper;
import com.se.sample.entity.model.Product;
import com.se.sample.repository.ProductRepository;
import com.se.sample.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Flux<Product> getAll() {
        return productRepository.findAll().switchIfEmpty(Flux.empty());
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
    public Mono save(final Product product) {
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


//    public Flux<User> fetchUsers(List<Integer> userIds) {
//        return Flux.fromIterable(userIds)
//                .parallel()
//                .runOn(Schedulers.elastic())
//                .flatMap(i -> findById(i))
//                .ordered((u1, u2) -> u2.getId() - u1.getId());
//    }
}
