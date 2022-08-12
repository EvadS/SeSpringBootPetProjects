package com.se.sample.model.resource;

import com.se.sample.controller.hateoas.BookmarkControllerV1;
import com.se.sample.entity.Bookmark;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

public class BookmarkResource extends RepresentationModel<BookmarkResource> implements Serializable {
    private final Bookmark bookmark;

    public BookmarkResource(Bookmark bookmark) {
        String username = bookmark.getAccount().getUsername();
        Long userId = bookmark.getAccount().getId();
        this.bookmark = bookmark;
        this.add(new Link(bookmark.getUri(), "bookmark-uri"));
        this.add(linkTo(BookmarkControllerV1.class, userId).withRel("bookmarks"));
        this.add(linkTo(methodOn(BookmarkControllerV1.class, userId).readBookmark(bookmark.getAccount().id, bookmark.getId())).withSelfRel());
    }

    public static Object valueOf(Bookmark s) {
        return null;
    }
}
