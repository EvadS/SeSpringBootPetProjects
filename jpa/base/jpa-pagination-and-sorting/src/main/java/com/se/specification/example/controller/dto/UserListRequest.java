package com.se.specification.example.controller.dto;

/**
 * @author Evgeniy Skiba on 20.06.2020
 * @project spring-data-jpa
 */

public class UserListRequest {
    public String search;
    public String street;
    public String city;
    private String email;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

