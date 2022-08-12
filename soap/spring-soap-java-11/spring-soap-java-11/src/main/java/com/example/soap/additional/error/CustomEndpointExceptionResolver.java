package com.example.soap.additional.error;

import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointExceptionResolver;

@Component
public class CustomEndpointExceptionResolver implements EndpointExceptionResolver {


    @Override
    public boolean resolveException(MessageContext messageContext, Object endpoint, Exception ex) {
        if (messageIsPing()) {
            return true;
        }
        return false;
    }

    private boolean messageIsPing() {
        return true;
    }
}