package com.se.sample.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

public class UserController {

//    @GetMapping("/get/{id}")
//    public Mono<ResponseEntity<Bucket>> getBucketById(@PathVariable(value = "id") String bucketId) {
//        return bucketRepository.findById(bucketId)
//                .map(saveBucket -> ResponseEntity.ok(saveBucket))
//                .defaultIfEmpty(ResponseEntity.notFound().build());
//            .switchIfEmpty(Mono.error(new BucketNotFoundException("Data not found")));
//            .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//    }
}
