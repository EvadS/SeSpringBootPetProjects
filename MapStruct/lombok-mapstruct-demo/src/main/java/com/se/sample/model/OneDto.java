package com.se.sample.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OneDto {

    private int id;
    private Integer version;
    private int projectId;
    private String title;
    private String code;
    private int sortOrder;

}