package com.se.sample.openapidemo.model;


import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class Address {

    @NotBlank(message = "SHould cCONTAINS SYMBOLS")
    @Schema(description = "Address line 1 of the contact.",
            example = "888 Constantine Ave, #54", required = true)
    @Size(max = 50)
    private String address1;

    private String address2;
    private String address3;
    private String postalCode;

    public Address() {
    }

    public String getAddress1() {
        return this.address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return this.address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return this.address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
