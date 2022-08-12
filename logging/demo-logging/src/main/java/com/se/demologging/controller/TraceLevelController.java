package com.se.demologging.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class TraceLevelController {

    private static final Logger LOG = LoggerFactory.getLogger(TraceLevelController.class);


    @GetMapping("/testLogLevel")
    public String testLogLevel() {
        LOG.warn("This is a warn message");
        LOG.trace("This is a TRACE log");
        LOG.debug("This is a DEBUG log");
        LOG.info("This is an INFO log");
        LOG.error("This is an ERROR log");

        return "Added some log output to console...";
    }
}