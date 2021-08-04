package com.se.sample.soap;


import com.se.sample.soap.model.GetCountryRequest;
import com.se.sample.soap.model.GetCountryResponse;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

//registers the class with Spring WS as a Web Service Endpoint
@Endpoint
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://www.sample.se.com/soap/model";

    private CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }
}
