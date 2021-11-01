package com.se.sample.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@ApiModel
@AllArgsConstructor
public enum Category {

    BOOKS("Books"),
    MUSIC("Music"),
    MOVIES("Movies"),
    GAMES("Games"),
    ELECTRONICS("Electronics"),
    COMPUTERS("Computers"),
    OFFICE("Office");

    @Getter private String value;
}
