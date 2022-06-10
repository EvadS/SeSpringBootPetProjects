package com.se.sample.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.dao.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.se.sample.Constant.INDEX;

@Slf4j
@Service
@RequiredArgsConstructor

public class PersonService {
    public static final String PEOPLE_INDEX = "people";
    private final RestHighLevelClient client;
    private final ObjectMapper objectMapper;

    Mono<IndexResponse> index(Person doc) {
        return indexDoc(doc)
                .doOnError(e -> log.error("Unable to index {}", doc, e));
    }

    private Mono<IndexResponse> indexDoc(Person doc) {
        return Mono.create(sink -> {
            try {
                doIndex(doc, listenerToSink(sink));
            } catch (JsonProcessingException e) {
                sink.error(e);
            }
        });
    }


    public Mono<IndexResponse> create(Person person) {
        return Mono.create(sink -> {
            try {

                doIndex(person, new ActionListener<IndexResponse>() {
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


    public Mono<Person> updatePerson(String id, Person person) {
        return updatePersonDoc(id, person)
                .filter(i -> !i.getGetResult().getSource().isEmpty())
                .map(i -> {
                            return objectMapper.convertValue(i.getGetResult().getSource(), Person.class);
                        }
                );
    }

    private Mono<UpdateResponse> updatePersonDoc(String id, Person person) {
        return Mono.create(sink -> {

            updateIndex(person, id, new ActionListener<UpdateResponse>() {
                @Override
                public void onResponse(UpdateResponse updateResponse) {
                    sink.success(updateResponse);
                }

                @Override
                public void onFailure(Exception e) {
                    sink.error(e);
                }
            });
        });
    }

    private void doIndex(Person doc, ActionListener<IndexResponse> listener) throws JsonProcessingException {

        UUID uuid = UUID.randomUUID();
        IndexRequest indexRequest = new IndexRequest(PEOPLE_INDEX)
                .id(uuid.toString());
        final String json = objectMapper.writeValueAsString(doc);
        indexRequest.source(json, XContentType.JSON);
        client.indexAsync(indexRequest, RequestOptions.DEFAULT, listener);
    }


    private void updateIndex(Person person, String id, ActionListener<UpdateResponse> listenerToSink) {
        Map<String, Object> documentObject = objectMapper.convertValue(person, Map.class);
        UpdateRequest updateRequest = new UpdateRequest(PEOPLE_INDEX, id)
                .fetchSource(true);
        updateRequest.doc(documentObject);

        client.updateAsync(updateRequest, RequestOptions.DEFAULT, listenerToSink);
    }

    public Mono<Person> findByUserId(String userId) {
        return Mono
                .<GetResponse>create(sink ->
                        client.getAsync(new GetRequest(PEOPLE_INDEX, userId), RequestOptions.DEFAULT, listenerToSink(sink))
                )
                .filter(GetResponse::isExists)
                .map(GetResponse::getSource)
                .map(map -> objectMapper.convertValue(map, Person.class));
    }


    public Mono<String> delete(String id) {
        return deleteDoc(id)
                .doOnError(e -> log.error("Unable to  delete person {}", id, e))
                .map(i -> i.getId());
    }

    private Mono<DeleteResponse> deleteDoc(String doc) {
        return Mono.create(sink -> {
            deletePeople(doc, listenerToSink(sink));
        });
    }


    private void deletePeople(String id, ActionListener<DeleteResponse> listenerToSink) {
        DeleteRequest deleteRequest = new DeleteRequest(PEOPLE_INDEX).id(id);
        client.deleteAsync(deleteRequest, RequestOptions.DEFAULT, listenerToSink);
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

    public Flux<Person> searchByCompanyName(String companyName) {

        SearchRequest searchRequest = new SearchRequest(PEOPLE_INDEX);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.from(0);
        // Сколько частей данных на странице
        searchSourceBuilder.size(2);

        QueryBuilder query = QueryBuilders.boolQuery()
                .should(new WildcardQueryBuilder("company.name", "*" + companyName + "*"));
        searchSourceBuilder.query(query);
        searchRequest.source(searchSourceBuilder);

        return doSearch(searchRequest, Person.class);
    }

    private <T> Flux<T> doSearch(SearchRequest searchRequest, Class<T> responseClazz) {
        Mono<List<T>> listMono = Mono
                .<SearchResponse>create(sink -> {
                    client.searchAsync(searchRequest, RequestOptions.DEFAULT, listenerToSink(sink));
                })
                .map(SearchResponse::getHits)
                .map(e -> {
                    log.info("e.totalHits:" + e.getHits().length);
                    SearchHit[] hits = e.getHits();
                    return Arrays.stream(hits).map(k -> {
                        Map<String, Object> source = k.getSourceAsMap();
                        return objectMapper.convertValue(source, responseClazz);
                    }).collect(Collectors.toList());
                });
        Flux<T> industryResearchFlux = listMono.flatMapMany(Flux::fromIterable);
        return industryResearchFlux;
    }

    private List<Person> getSearchResult(SearchResponse response) {
        SearchHit[] searchHit = response.getHits().getHits();
        log.info("found items:{}", searchHit.length);
        List<Person> profileDocuments =
                Arrays.stream(searchHit)
                        .map(hit -> objectMapper.convertValue(hit.getSourceAsMap(), Person.class))
                        .collect(Collectors.toList());
        log.debug("Found: {} items", profileDocuments.size());
        return profileDocuments;
    }

    public Flux<Person> findAll() {
        SearchRequest searchRequest = new SearchRequest(PEOPLE_INDEX);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        return doSearch(searchRequest, Person.class);
    }
}
