package com.se.client;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Evgeniy Skiba on 27.10.2020
 * @project demo-client
 */
public interface GreetingController {
    @RequestMapping("/greeting")
    String greeting();
}