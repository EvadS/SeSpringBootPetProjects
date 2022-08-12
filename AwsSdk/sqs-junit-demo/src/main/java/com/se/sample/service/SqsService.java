package com.se.sample.service;


import com.se.sample.dto.Quote;

public interface SqsService {
    void saveQuote(Quote incomingQuote,
                   String messageId,
                   String approximateFirstReceiveTimestamp);
}