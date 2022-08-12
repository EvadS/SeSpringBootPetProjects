package com.se.maual.pagging.enums;

/**
 * @author Evgeniy Skiba on 20.06.2020
 * @project spring-data-jpa
 */
public enum OrderBy {
    ID("id"), USERID("userId"), EMAIL("email"), ALIAS("alias");
    private String OrderByCode;
    private OrderBy(String orderBy) {
        this.OrderByCode = orderBy;
    }
    public String getOrderByCode() {
        return this.OrderByCode;
    }
}
