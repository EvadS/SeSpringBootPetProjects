package com.se.sample.models;

/**
 * @author Skiba Evgeniy
 * @date 13.09.2021
 */

public class RerlfAboutResponse extends BaseResponse{
    private int id;
    private String name;

    public RerlfAboutResponse() {
    }

    public RerlfAboutResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
