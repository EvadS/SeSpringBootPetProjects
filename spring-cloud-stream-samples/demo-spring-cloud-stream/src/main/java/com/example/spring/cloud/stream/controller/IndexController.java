package com.example.spring.cloud.stream.controller;


import com.example.spring.cloud.stream.service.Producer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class IndexController {

    private final Producer producer;

    IndexController(Producer producer) {
        this.producer = producer;
    }

    @GetMapping(value = "/publish/{message}")
    @ResponseBody
    public String sendMessageToKafka(@PathVariable("message") String message) {
        this.producer.sendMessage(message);
        return "success";
    }
}
