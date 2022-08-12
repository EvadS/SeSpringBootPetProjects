package com.se.sample.model.resource;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookmarkResourceList extends RepresentationModel<BookmarkResourceList> implements Serializable {

    private List<BookmarkResource> list = new ArrayList<>();

    public List<BookmarkResource> getList() {
        return list;
    }

    public void setList(List<BookmarkResource> list) {
        this.list = list;
    }
}
