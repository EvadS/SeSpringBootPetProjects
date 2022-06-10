package com.se.sample.support;

import com.se.sample.controller.advice.FluxRestException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ElasticAdapter {

    private final RestHighLevelClient esClient;

    /**
     * create new item
     *
     * @param index          index name
     * @param id             unique identifier
     * @param documentObject document object value
     * @return response
     * @throws IOException
     */
    public IndexResponse create(String index, String id, Map<String, Object> documentObject) throws IOException {
        IndexRequest request = new IndexRequest(index)
                .id(id)
                .source(documentObject);

        log.debug("Saving document in index {}: {}", index, documentObject);
        return esClient.index(request, RequestOptions.DEFAULT);
    }

    public GetResponse get(String index, String id) throws IOException {
        GetRequest request = new GetRequest(index, id);
        return esClient.get(request, RequestOptions.DEFAULT);
    }

    public UpdateResponse update(String index, String id,  Map<String, Object> documentObject, boolean fetchSource) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest(index, id)
                .fetchSource(fetchSource);
        updateRequest.doc(documentObject);
        return esClient.update(updateRequest, RequestOptions.DEFAULT);
    }


    public DeleteResponse delete(String index, String id) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(index, id);
        return esClient.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    /**
     * check is document exists
     *
     * @param index index name
     * @param id    unique identifier
     * @throws IOException
     */
    boolean existDoc(String index, String id) throws IOException {
        GetRequest getRequest = new GetRequest(index, id);

        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");

        return esClient.exists(getRequest, RequestOptions.DEFAULT);

    }

    SearchResponse search(SearchRequest request) throws IOException {
        log.debug("Search request: {}", request);
        return esClient.search(request, RequestOptions.DEFAULT);
    }


    /******************************************************
     *    Async block
     *******************************************************/

    /**
     * check index exists
     *
     * @param index index name
     * @return
     */
    public Optional<Boolean> existsIndex(String index) {

        log.info("existsIndex: index {}", index);

        try {
            GetIndexRequest request = new GetIndexRequest(index);
            return Optional.of(esClient.indices().exists(request, RequestOptions.DEFAULT));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    private void getById(String index, String id) {

        GetRequest getRequest = new GetRequest();

        esClient.getAsync(getRequest, RequestOptions.DEFAULT, new ActionListener<GetResponse>() {
            @Override
            public void onResponse(GetResponse documentFields) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }


    public Mono<IndexResponse> indexAsync(String index, String id, Object documentObject) {
        return Mono.create(sink -> {

            IndexRequest indexRequest = new IndexRequest(index)
                    .id(id)
                    .source(documentObject, XContentType.JSON);

            esClient.indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
                @Override
                public void onResponse(IndexResponse indexResponse) {
                    sink.success(indexResponse);
                }

                @Override
                public void onFailure(Exception e) {
                    sink.error(e);
                }
            });
        });
    }


    public Mono<GetResponse> getAsync(String index, String id) {
        return Mono.create(monoSink -> {
            GetRequest request = new GetRequest(index, id);
            esClient.getAsync(request, RequestOptions.DEFAULT, new ActionListener<GetResponse>() {
                @Override
                public void onResponse(GetResponse result) {
                    monoSink.success(result);
                }

                @Override
                public void onFailure(Exception ex) {
                    log.error("Exception while executing get: {}", ex.getMessage());
                    monoSink.error(ex);
                }
            });
        });
    }

    public Mono<IndexResponse> updateAsync(String index, String id, Object documentObject) {
        return Mono.create(sink -> {

            UpdateRequest request = new UpdateRequest(index, id)
                    .fetchSource(true);
            request.doc(documentObject, XContentType.JSON);

            esClient.updateAsync(request, RequestOptions.DEFAULT, new ActionListener<UpdateResponse>() {
                @Override
                public void onResponse(UpdateResponse updateResponse) {
                    if (!updateResponse.status().equals(RestStatus.ACCEPTED)) {
                        log.warn("Get unsuccessful. Response Code: {}", updateResponse.status());
                    } else {
                        log.debug("Get completed: {}", updateResponse.getGetResult());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    sink.error(e);
                }
            });
        });
    }


    public Mono<DeleteResponse> deleteAsync(String index, String id) throws IOException {
        return Mono.create(sink -> {
            DeleteRequest request = new DeleteRequest(index, id);
            esClient.deleteAsync(request, RequestOptions.DEFAULT, new EduAppDeleteListen());
        });
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


    public boolean exists(String indexName) {
        GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
        boolean exists = false;
        try {
            exists = esClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exists;
    }

    /**
     * check is index exists
     *
     * @param indexName
     * @return
     */
    public Mono<Boolean> existsAsync(String indexName) {
        return Mono.create(sink -> {
            GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
            esClient.indices().existsAsync(getIndexRequest, RequestOptions.DEFAULT, new ExistsAsyncListen());
        });
    }

    public Mono<CreateIndexResponse> createIndexAsync(String index) {
        return Mono.create(sink -> {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
            esClient.indices().createAsync(createIndexRequest, RequestOptions.DEFAULT, new CreateActionListener<CreateIndexResponse>(sink));
        });

    }

    public boolean deleteIndex(String indexName) {
        log.info("Deleting index " + indexName);
        DeleteRequest request = new DeleteRequest(indexName);

        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
        try {
            AcknowledgedResponse deleteResponse = esClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
            log.info("Index deleted:{}", deleteResponse);
            return deleteResponse.isAcknowledged();
        } catch (IOException e) {
            log.info("No such index: " + indexName);
            return false;
        }
    }

    public Mono<Boolean> deleteIndexAsync(String indexName) {

        return Mono.create(sink -> {
            log.info("Deleting index " + indexName);
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
            esClient.indices().deleteAsync(deleteIndexRequest, RequestOptions.DEFAULT, new ActionListener<AcknowledgedResponse>() {
                @Override
                public void onResponse(AcknowledgedResponse response) {
                    sink.success(response.isAcknowledged());
                }

                @Override
                public void onFailure(Exception e) {
                    sink.error(e);
                }
            });
        });

    }

    public Boolean createIndex(String indexName) {

        org.elasticsearch.client.indices.CreateIndexRequest request = new org.elasticsearch.client.indices.CreateIndexRequest(indexName);
        try {

            CreateIndexResponse createIndexResponse = esClient.indices().create(request, RequestOptions.DEFAULT);
            return createIndexResponse.isAcknowledged();
        } catch (Exception e) {
            log.error("[createIndex]", e);
            return false;
        }
    }

    public String[] getAllIndexes() throws IOException {
        GetIndexRequest request = new GetIndexRequest("*");
        GetIndexResponse response = esClient.indices().get(request, RequestOptions.DEFAULT);
        return response.getIndices();
    }

    public Mono<String[]> getAllIndexesAsync() {

        return Mono.create(sink -> {
            GetIndexRequest request = new GetIndexRequest("*");

            esClient.indices().getAsync(request, RequestOptions.DEFAULT,
                    new ActionListener<GetIndexResponse>() {
                        @Override
                        public void onResponse(GetIndexResponse response) {
                            sink.success(response.getIndices());
                        }

                        @Override
                        public void onFailure(Exception e) {
                            sink.error(e.getCause());
                        }
                    });
        });
    }

    public SearchResponse search(SearchSourceBuilder searchSourceBuilder, String indexName) throws IOException {
        SearchRequest searchRequest = new SearchRequest(indexName);
        searchRequest.source(searchSourceBuilder);
      return esClient.search(searchRequest, RequestOptions.DEFAULT);
    }


    private static class EduAppDeleteListen implements ActionListener<DeleteResponse> {
        @Override
        public void onResponse(DeleteResponse deleteResponse) {
            log.debug("Asynchronous modification succeeded ：" + deleteResponse);
        }

        @Override
        public void onFailure(Exception e) {

            System.out.println(" Asynchronous modification failed ：" + e.getMessage());
        }
    }

    private static class ExistsAsyncListen implements ActionListener<Boolean> {
        private MonoSink<Boolean> sink;

        @Override
        public void onResponse(Boolean result) {
            sink.success(result);
        }

        @Override
        public void onFailure(Exception e) {
            log.error("BooleanActionListener error: {}", e.getMessage());
        }
    }

    @Slf4j
    @AllArgsConstructor
    private static class CreateActionListener<T> implements ActionListener<CreateIndexResponse> {

        private final MonoSink<CreateIndexResponse> sink;

        @Override
        public void onResponse(CreateIndexResponse response) {

            if (!response.isAcknowledged()) {
                log.error("Execution failed, result={}", response.toString());
                sink.error(new FluxRestException("Elasticsearch execution failed. result=" + response.toString()));
            } else {
                log.debug("Execution complete");
                sink.success(response);
            }
        }

        @Override
        public void onFailure(Exception e) {
            sink.error(e);
        }
    }

}
