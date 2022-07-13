package com.se.sample.employeeservice.service;

import com.se.sample.employeeservice.model.MyModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MyModelService {
    Mono<MyModel> findMyModelById(String id);

    Flux<MyModel> findAllMyModels(String field, String value);

    Mono<MyModel> saveMyModel(MyModel myModel);

    Mono<String> deleteMyModelById(String id);
}
