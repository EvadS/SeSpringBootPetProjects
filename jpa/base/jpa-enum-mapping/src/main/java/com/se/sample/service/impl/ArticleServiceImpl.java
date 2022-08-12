package com.se.sample.service.impl;

import com.se.sample.exception.ResourceNotFoundException;
import com.se.sample.mapper.ArticleMapper;
import com.se.sample.model.domain.Article;
import com.se.sample.model.request.ArticleRequest;
import com.se.sample.model.response.ArticleResponse;
import com.se.sample.model.response.ArticleResponseList;
import com.se.sample.repository.ArticleRepository;
import com.se.sample.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public ArticleResponse createArticle(ArticleRequest articleRequest) {
        Article article = ArticleMapper.INSTANCE.ArticleRequestToArticle(articleRequest);
        articleRepository.save(article);


        return  ArticleMapper.INSTANCE.ArticleToArticleResponse(article);
    }

    @Override
    public ArticleResponse updateArticle(Long articleId, ArticleRequest articleRequest) {
        return null;
    }

    @Override
    public void deleteArticle(long id) {

    }

    @Override
    public ArticleResponse getById(Long articleId) {
        Article article =  articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article", "id", articleId));
        return ArticleMapper.INSTANCE.ArticleToArticleResponse(article);
    }

    @Override
    public Page<ArticleResponse> getArticles(Pageable pageable, String filter) {
        // TODO: filter not used
        Page<Article> userPage = articleRepository.findAll(pageable);
        return userPage.map(ArticleMapper.INSTANCE::ArticleToArticleResponse);
    }

    @Override
    public Page<ArticleResponse> getArticles(Pageable pageable) {
        Page<Article> userPage = articleRepository.findAll(pageable);
        return userPage.map(ArticleMapper.INSTANCE::ArticleToArticleResponse);
    }

    @Override
    public List<ArticleResponse> getAll() {
        // I don't want new mapper
        List<ArticleResponse> collect = articleRepository.findAll().stream()
                .map(ArticleMapper.INSTANCE::ArticleToArticleResponse)
                .collect(Collectors.toList());

        return collect;
    }
}
