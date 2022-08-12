package com.se.sample.controller;

import com.se.sample.service.IndexService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/admin")
@Tag(name = "Admin API",
        description = "operation with indexes ..."
)
@RequiredArgsConstructor
public class AdminController {

    private final IndexService indexService;

    @ResponseBody
    @GetMapping("/async/indexes-all")
    public Mono<String[]> allIndexesAsync() {
        return indexService.getAllIndexesAsync();
    }

    @ResponseBody
    @GetMapping("/async/index/exists/{index}")
    public Mono<Boolean> indexExistsAsync(@PathVariable(value = "index") String indexName) {
        return indexService.indexExistsAsync(indexName);
    }

    @ResponseBody
    @GetMapping("/async/index/create/{index}")
    public Mono<Boolean> indexCreateAsync(@PathVariable(value = "index") String indexName) {
        return indexService.createIndexAsync(indexName)
                .doOnError(error -> ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(error.getMessage()));
    }

    @ResponseBody
    @GetMapping("/async/index/drop/{index}")
    public Mono<Boolean> indexDropAsync(@PathVariable(value = "index") String indexName) {
        return indexService.dropIndexAsync(indexName);
    }

    @ResponseBody
    @Operation(summary = "indexes", description = "all indexes")
    @GetMapping("/indexes-all")
    public String[] allIndexes() {
        return indexService.getAllIndexes();
    }

    @ResponseBody
    @GetMapping("/index/exists/{index}")
    public Boolean indexExists(@PathVariable(value = "index") String indexName) {
        return indexService.indexExists(indexName);
    }

    @ResponseBody
    @GetMapping("/index/create/{index}")
    public Boolean indexCreateExists(@PathVariable(value = "index") String indexName) {
        return indexService.createIndex(indexName);
    }

    @ResponseBody
    @GetMapping("/index/drop/{index}")
    public Boolean indexDrop(@PathVariable(value = "index") String indexName) {
        return indexService.dropIndex(indexName);
    }
}
