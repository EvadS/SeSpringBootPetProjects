package com.se.sample.rest.client.component;

import com.se.sample.rest.client.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @PostConstruct
    private  void  init(){
        logger.info("** PostConstruct  RestTemplateResponseErrorHandler");
    }
    private final Logger logger = LoggerFactory.getLogger(RestTemplateResponseErrorHandler.class);


    // TODO: when response code != 200  here check response
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {

        HttpStatus statusCode = getHttpStatusCode(response);
        switch (statusCode.series()) {
            case CLIENT_ERROR:
                throw new HttpClientErrorException(statusCode, response.getStatusText(),
                        response.getHeaders(), getResponseBody(response), getCharset(response));
            case SERVER_ERROR:
                throw new HttpServerErrorException(statusCode, response.getStatusText(),
                        response.getHeaders(), getResponseBody(response), getCharset(response));
            default:
                throw new RestClientException("Unknown status code [" + statusCode + "]");
        }
    }

    private Charset getCharset(ClientHttpResponse response) {
        return  null;
    }

    private byte[] getResponseBody(ClientHttpResponse response) {
        return ((ResponseEntity) response).getBody().toString().getBytes();
    }


    private HttpStatus getHttpStatusCode(ClientHttpResponse clienthttpresponse) throws IOException {
        return  clienthttpresponse.getStatusCode();
    }


    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException {
        logger.info("HERE 22 ");
        if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            int a = 0;
            // handle SERVER_ERROR
        } else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            // handle CLIENT_ERROR

        }
            int aaa = 0;

            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NotFoundException("хз");
            }
        }
    }
