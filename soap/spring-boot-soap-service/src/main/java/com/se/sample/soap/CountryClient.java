package com.se.sample.soap;

import com.se.sample.springsoap.gen.GetCountryRequest;
import com.se.sample.springsoap.gen.GetCountryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class CountryClient extends WebServiceGatewaySupport {

    private static final Logger logger = LoggerFactory.getLogger(CountryClient.class);

    public GetCountryResponse getCountry(String country) {

        GetCountryRequest request = new GetCountryRequest();
        request.setName(country);

        logger.info("Requesting information for " + country);

        GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate().marshalSendAndReceive(request);

        return response;
    }
}