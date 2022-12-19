package com.se.samplebase.service.impl;

import com.se.samplebase.service.AnimalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("bird")
public class BirdServiceImpl implements AnimalService {
    private static final Logger logger = LoggerFactory.getLogger(BirdServiceImpl.class);

    @Override
    public void eat() {
        logger.info("bird is eating");
    }
}