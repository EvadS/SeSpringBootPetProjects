package com.se.sample.appicationprops;

public class BrandDefaultValue {
    private String value;
    private String type;
    private Class typeClass;

    public BrandDefaultValue() {
    }

    public BrandDefaultValue(String value, String type, Class typeClass) {
        this.value = value;
        this.type = type;
        this.typeClass = typeClass;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Class getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(Class typeClass) {
        this.typeClass = typeClass;
    }
}
