package com.se.sample.mapper;

import com.se.sample.model.domain.Article;
import com.se.sample.model.enums.Priority;
import com.se.sample.model.enums.Status;
import com.se.sample.model.request.ArticleRequest;
import com.se.sample.model.response.ArticleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    @Mappings({
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "status",  source = "status", qualifiedByName = "fromStatus" ),
            @Mapping(target = "type",  source = "type"),
            @Mapping(target = "priority",  source = "priority", qualifiedByName = "fromPriority"),
            @Mapping(target = "category", source = "category"),
            @Mapping(target = "priorityValue",  ignore = true),
            @Mapping(target = "id", ignore = true)
    })
    Article ArticleRequestToArticle(ArticleRequest articleRequest);


    @Named("fromStatus")
    default Status qualifiedStatus(int ordinal) {
        return Status.values()[ordinal];
    }

    @Named("fromPriority")
    default Priority qualifiedPriority(int priorityId) {
        return Priority.of(priorityId);
    }


    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "title", source = "title",  defaultValue = "NOT_SET"),
            @Mapping(target = "status", source = "status"),
            @Mapping(target = "category", source = "category"),
            @Mapping(target = "priority", source = "priority.priority")
    })
    ArticleResponse ArticleToArticleResponse(Article article);




}
