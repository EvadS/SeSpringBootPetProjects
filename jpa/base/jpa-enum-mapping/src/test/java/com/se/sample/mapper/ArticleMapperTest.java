package com.se.sample.mapper;

import com.se.sample.model.domain.Article;
import com.se.sample.model.enums.Category;
import com.se.sample.model.enums.Priority;
import com.se.sample.model.enums.Status;
import com.se.sample.model.enums.Type;
import com.se.sample.model.request.ArticleRequest;
import org.junit.Assert;
import org.junit.Test;

public class ArticleMapperTest {

    public static final Status APPROVED = Status.APPROVED;
    private static final ArticleMapper articleMapper = ArticleMapper.INSTANCE;
    private static final Priority PRIORITY = Priority.MEDIUM;
    public static final Category CATEGORY_MUSIC = Category.MUSIC;
    public static final String TITLE = "TITLE";

    @Test
    public void article_request_to_article_should_work_correct() {

        ArticleRequest articleRequest = buildArticleRequest();

        Article article = articleMapper.ArticleRequestToArticle(articleRequest);

        Assert.assertEquals(TITLE, article.getTitle());
        Assert.assertEquals(APPROVED, article.getStatus());
        Assert.assertEquals(Type.INTERNAL, article.getType());
        Assert.assertEquals(PRIORITY, article.getPriority());
        Assert.assertEquals(CATEGORY_MUSIC, article.getCategory());
    }

    private ArticleRequest buildArticleRequest() {
        ArticleRequest articleRequest = new ArticleRequest();

        articleRequest.setCategory(CATEGORY_MUSIC);
        articleRequest.setPriority(PRIORITY.getPriority());

        articleRequest.setStatus(APPROVED.ordinal());
        articleRequest.setTitle(TITLE);
        articleRequest.setType(Type.INTERNAL);


        return articleRequest;

    }
}