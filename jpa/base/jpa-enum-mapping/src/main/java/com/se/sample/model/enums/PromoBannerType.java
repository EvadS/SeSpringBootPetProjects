package com.se.sample.model.enums;


public enum PromoBannerType {
    SPORT("S"), GAME("G"), MARKET("M");

    private final String code;

    PromoBannerType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}