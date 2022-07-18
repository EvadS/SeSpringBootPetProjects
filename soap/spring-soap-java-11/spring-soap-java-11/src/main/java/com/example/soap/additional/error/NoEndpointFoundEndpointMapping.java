package com.example.soap.additional.error;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInvocationChain;
import org.springframework.ws.server.EndpointMapping;
import org.springframework.ws.soap.SoapEnvelope;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class NoEndpointFoundEndpointMapping implements EndpointMapping {
    @Override
    public EndpointInvocationChain getEndpoint(MessageContext messageContext) throws Exception {
        WebServiceMessage request = messageContext.getRequest();

        if (request instanceof SoapMessage) {
            SoapMessage soapMessage = (SoapMessage) request;
            SoapEnvelope envelope = soapMessage.getEnvelope();
            SoapHeader header = envelope.getHeader();
            WebServiceMessage response = messageContext.getResponse();
            int a =0;
//            try {
//                Marshaller m = jaxbContext.createMarshaller();
//                m.marshal(tzc, header.getResult());
//            } catch (JAXBException e) {
//                log.error("JAXBException raised while attempting to add TimeZoneContext to soap header " + tzc, e);
//                throw new ExchangeWebServicesRuntimeException("JAXBException raised while attempting to add TimeZoneContext to soap header " + tzc, e);
//            }
        }
       throw new CustomEndpointNotFoundException("");
    }

}
