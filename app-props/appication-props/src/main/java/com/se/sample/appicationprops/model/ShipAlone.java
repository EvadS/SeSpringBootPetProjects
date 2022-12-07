package com.se.sample.appicationprops.model;


public enum ShipAlone
    I, Y, N, F  ;
    public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"ShipAlone\",\"namespace\":\"com.fd.acquisition.common.product.model\",\"symbols\":[\"I\",\"Y\",\"N\",\"F\"]}");
    public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
    public org.apache.avro.Schema getSchema() { return SCHEMA$; }
}
