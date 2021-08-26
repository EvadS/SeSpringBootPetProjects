package com.se.sample.soap;



import com.se.sample.springsoap.gen.GetCountryRequest;
import com.se.sample.springsoap.gen.GetCountryResponse;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

//registers the class with Spring WS as a Web Service Endpoint
@Endpoint
public class CountryEndpoint {

    //http://www.sample.se.com/springsoap/gen
    private static final String NAMESPACE_URI = "http://www.sample.se.com/springsoap/gen";
//"http://www.baeldung.com/springsoap/gen
//  private static final String NAMESPACE_URI = "http://www.baeldung.com/springsoap/gen";

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
