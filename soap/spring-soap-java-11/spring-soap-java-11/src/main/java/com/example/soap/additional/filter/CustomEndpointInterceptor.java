package com.example.soap.additional.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.SoapBody;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

@Component
public class CustomEndpointInterceptor implements EndpointInterceptor {

    public static final Log LOG = LogFactory.getLog(CustomEndpointInterceptor.class);

    @Autowired
    private Jaxb2Marshaller jaxb2Marshaller;

    @Override
    public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
        LOG.info("Endpoint Request Handling");

        boolean proceed = true;
        SaajSoapMessage saajSoapMessage = (SaajSoapMessage) messageContext.getRequest();
        SoapBody requestBody = saajSoapMessage.getSoapBody();

//    Object obj = marshaller.unmarshal(requestBody.getPayloadSource());
//        SoapBody requestBody = saajSoapMessage.getSoapBody();
//        Object obj = jaxb2Marshaller.unmarshal(requestBody.getPayloadSource());
//
//
//        if (obj instanceof ConfirmOrderRequest ) {
//            ConfirmOrderRequest cor = (ConfirmOrderRequest ) obj;
//
//            String orderId = cor.getOrderId();
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
        LOG.info("Endpoint Response Handling");
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
        LOG.info("Endpoint Exception Handling");
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) throws Exception {
        LOG.info("Execute code after completion");
    }

}