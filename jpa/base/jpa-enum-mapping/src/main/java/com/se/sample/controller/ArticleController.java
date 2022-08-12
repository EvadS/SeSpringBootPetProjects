package com.se.sample.controller;

import com.se.sample.model.request.ArticleRequest;
import com.se.sample.model.response.ArticleResponse;
import com.se.sample.model.response.ArticleResponseList;
import com.se.sample.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/article")
@Api(value = "Article api management")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping("/article")
    public ResponseEntity<?> createArticle(@Valid @RequestBody ArticleRequest articleRequest) {
        ArticleResponse articleResponse = articleService.createArticle(articleRequest);
        return new ResponseEntity<>(articleResponse, HttpStatus.CREATED);
    }

    @PutMapping("/article/{id}")
    @ApiOperation(value = "Update article.", nickname = "update-article",
            notes = "Update article.",             tags = {})
    public ResponseEntity<?> updateArticle(
                                           @ApiParam(value = "ID of article", required = true, example = "123")
                                           @PathVariable(value = "id") @NotNull Long articleId,
                                           @ApiParam(value = "Article details for update", required = true)
                                           @Valid @RequestBody ArticleRequest articleRequest) {
        ArticleResponse articleResponse = articleService.updateArticle(articleId, articleRequest);
        return new ResponseEntity<>(articleResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/article/{id}")
    @ApiOperation(value = "Delete article.", nickname = "delete",
            notes = "Delete article by id.",
            tags = {})
    public ResponseEntity<?> deleteArticle(
             @ApiParam(value = "ID of article to return", required = true, example = "123")
             @PathVariable  (value = "id") @NotNull Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(value = "/article/{id}")
    @ApiOperation(value = "Article.", nickname = "article-get",
            notes = "Get article by id.",
            tags = {})
    public ResponseEntity<?> getArticleById(
            @ApiParam(value = "ID of article to return", required = true, example = "123")
            @PathVariable(value = "id") Long articleId) {
        ArticleResponse articleResponse = articleService.getById(articleId);
        return ResponseEntity.ok(articleResponse);
    }

    @GetMapping(value = "/list")
    @ApiOperation(value = "Current articles", nickname = "list",
            notes = "Articles list.",
            tags = {})
    public ResponseEntity<?> getArticles() {
        List<ArticleResponse>  articleResponseList = articleService.getAll();
        return ResponseEntity.ok(articleResponseList);
    }


    @GetMapping(value = "/paged-base")
    @ApiOperation(value = "Articles list with pagination", nickname = "paged-base",
            notes = "Articles list with pageable interface.",
            tags = {})
    public ResponseEntity<Page<ArticleResponse>> getImages(Pageable pageable) {
        Page<ArticleResponse> articleResponsePage = articleService.getArticles(pageable);
        return ResponseEntity.ok(articleResponsePage);
    }

    //Manual page
    @ApiOperation(value = "Articles list with pagination", nickname = "pagged-base",
            notes = "Manual pageable articles list with  manual pageable.",
            tags = {})
    @RequestMapping(value = "/paged", method = RequestMethod.GET)
    public ResponseEntity<Page<ArticleResponse>> getArticles(
            @ApiParam(value = "Current page number.", example = "0", required = false)
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @ApiParam(value = "Number entities on page.",  example = "0", required = false)
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @ApiParam(value = "Field records will be sorted based on. By default is priority.",
                    required = false ,example = "0" )
            @RequestParam(required = false, defaultValue = "") String filter) {

        Pageable pageable = (filter == null) ? PageRequest.of(page, limit) : PageRequest.of(page, limit, Sort.by("title"));
        Page<ArticleResponse> articleResponsePage = articleService.getArticles(pageable, filter);

        return ResponseEntity.ok(articleResponsePage);
    }
}
