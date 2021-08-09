package com.se.product.service.component;


import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by Evgeniy Skiba on 26.05.21
 */

// TODO: implement
@Component
public class OkStatusCodeFilteringPlugin
    //    implements OperationBuilderPlugin
{
//
//    @Override
//    public void apply(OperationContext operationContext) {
//
//        if (!operationContext.httpMethod().equals(HttpMethod.GET)) {
//            operationContext
//                    .operationBuilder()
//                    .build()
//                    .getResponseMessages()
//                    .removeIf(responseMessage -> Arrays.asList(
//                            HttpStatus.NOT_FOUND.value(),
//                            HttpStatus.CONFLICT.value()
//                    ).contains(responseMessage.getCode()));
//        }
//    }
//
//    @Override
//    public boolean supports(DocumentationType documentationType) {
//        return true;
//    }
}