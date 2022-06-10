package com.se.sample.fluxes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ElasticAdapter {


    private final RestHighLevelClient client;
    private final ObjectMapper objectMapper;

    Mono<IndexResponse> index(Person doc) {
        return Mono.create(sink -> {
            try {

                doIndex(doc, new ActionListener<IndexResponse>() {
                    @Override
                    public void onResponse(IndexResponse indexResponse) {
                        sink.success(indexResponse);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        sink.error(e);
                    }
                });
            } catch (JsonProcessingException e) {
                sink.error(e);
            }
        });
    }

    private void doIndex(Person doc, ActionListener<IndexResponse> listener) throws JsonProcessingException {
        //  final IndexRequest indexRequest = new IndexRequest("people",  doc.getUsername());

        UUID uuid = UUID.randomUUID();
        IndexRequest indexRequest = new IndexRequest("people")
                .id(uuid.toString());
        final String json = objectMapper.writeValueAsString(doc);
        indexRequest.source(json, XContentType.JSON);
        client.indexAsync(indexRequest, RequestOptions.DEFAULT, listener);
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

    Mono<Person> findByUserId(String userId) {
        return Mono
                .<GetResponse>create(sink ->
                        client.getAsync(new GetRequest("people", userId), RequestOptions.DEFAULT, listenerToSink(sink))
                )
                .filter(GetResponse::isExists)
                .map(GetResponse::getSource)
                .map(map -> objectMapper.convertValue(map, Person.class));
    }
}
