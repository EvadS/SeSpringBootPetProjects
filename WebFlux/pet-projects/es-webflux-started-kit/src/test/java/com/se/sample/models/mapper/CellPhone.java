package com.se.sample.models.mapper;

import java.util.Objects;

public class CellPhone {
    String vendor;
    String model;

    public CellPhone(String vendor, String model) {
        this.vendor = vendor;
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellPhone cellPhone = (CellPhone) o;
        return Objects.equals(vendor, cellPhone.vendor) && Objects.equals(model, cellPhone.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendor, model);
    }
}
