package com.se.product.service.endpoint;


import org.springframework.ws.server.endpoint.annotation.Endpoint;

/**
 * register the class with Spring WS as a Web Service Endpoint
 */
@Endpoint
public class PriceEndpoint {
    private static final String NAMESPACE_URI = "http://www.service.product.se.com/model/soap";
//"http://www.baeldung.com/springsoap/gen
//  private static final String NAMESPACE_URI = "http://www.baeldung.com/springsoap/gen";

}
