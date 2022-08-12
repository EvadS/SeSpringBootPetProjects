package com.se.sample.service;


import com.se.sample.dto.Quote;
import com.se.sample.entity.QuoteEntity;
import com.se.sample.repository.QuoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SqsServiceImpl implements SqsService {
    private static final Logger LOG = LoggerFactory.getLogger(SqsServiceImpl.class);

    private final QuoteRepository quoteRepository;

    public SqsServiceImpl(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Override
    public void saveQuote(Quote incomingQuote, String messageId, String approximateFirstReceiveTimestamp) {
        if (quoteRepository.existsByAwsMessageId(messageId)) {
            LOG.warn("Quote with AWS Message ID {} already exists", messageId);
        } else {
            QuoteEntity quote = new QuoteEntity(
                    incomingQuote.getText(),
                    incomingQuote.getAuthor(),
                    messageId,
                    toInstant(approximateFirstReceiveTimestamp)
            );
            LOG.info("Saving quote with AWS Message ID {}", messageId);
            quoteRepository.save(quote);
        }
    }

    private Instant toInstant(String dateAsStr) {
        return Instant.ofEpochMilli(Long.parseLong(dateAsStr));
    }
}