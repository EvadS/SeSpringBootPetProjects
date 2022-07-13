package com.se.sample.employeeservice.service.impl;

import com.se.sample.employeeservice.model.MyModel;
import com.se.sample.employeeservice.service.MyModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

import static com.se.sample.employeeservice.util.Constants.DEFAULT_ES_DOC_TYPE;
import static com.se.sample.employeeservice.util.Constants.MYMODEL_ES_INDEX;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyModelServiceImpl implements MyModelService {

    private final ReactiveElasticsearchOperations reactiveElasticsearchOperations;
    private final ReactiveElasticsearchClient reactiveElasticsearchClient;

    @PostConstruct
    private void checkIndexExists(){

        GetIndexRequest request = new GetIndexRequest();
        request.indices(MYMODEL_ES_INDEX);

        reactiveElasticsearchClient.indices()
                .existsIndex(request)
                .doOnError(throwable -> log.error(throwable.getMessage(), throwable))
                .flatMap(indexExists -> {
                    log.info("Index {} exists: {}", MYMODEL_ES_INDEX, indexExists);
                    if (!indexExists)
                        return createIndex();
                    else
                        return Mono.empty();
                })
                .block();
    }

    private Mono<?> createIndex() {
        CreateIndexRequest request = new CreateIndexRequest();
        request.index(MYMODEL_ES_INDEX);
        request.mapping(DEFAULT_ES_DOC_TYPE,
                "{\n" +
                        "  \"properties\": {\n" +
                        "    \"timestamp\": {\n" +
                        "      \"type\": \"date\",\n" +
                        "      \"format\": \"epoch_millis||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}",
                XContentType.JSON);

        return reactiveElasticsearchClient.indices()
                .createIndex(request)
                .doOnSuccess(aVoid -> log.info("Created Index {}", MYMODEL_ES_INDEX))
                .doOnError(throwable -> log.error(throwable.getMessage(), throwable));
    }


    @Override
    public Mono<MyModel> findMyModelById(String id) {
        return reactiveElasticsearchOperations.get(
                id,
                MyModel.class,
                IndexCoordinates.of(MYMODEL_ES_INDEX)
        ).doOnError(throwable -> log.error(throwable.getMessage(), throwable));
    }

    @Override
    public Flux<MyModel> findAllMyModels(String field, String value) {
        return null;
    }

    @Override
    public Mono<MyModel> saveMyModel(MyModel myModel) {
        return null;
    }

    @Override
    public Mono<String> deleteMyModelById(String id) {
        return null;
    }
}
