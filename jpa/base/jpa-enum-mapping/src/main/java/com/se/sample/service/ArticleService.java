package com.se.sample.service;

import com.se.sample.model.domain.Article;
import com.se.sample.model.request.ArticleRequest;
import com.se.sample.model.response.ArticleResponse;
import com.se.sample.model.response.ArticleResponseList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {

    ArticleResponse createArticle (ArticleRequest articleRequest);

    ArticleResponse updateArticle(Long articleId, ArticleRequest articleRequest);

    void deleteArticle(long id);

    ArticleResponse getById(Long articleId);

    Page<ArticleResponse> getArticles(Pageable pageable, String filter);

    Page<ArticleResponse> getArticles(Pageable pageable);

    List<ArticleResponse> getAll();
}
