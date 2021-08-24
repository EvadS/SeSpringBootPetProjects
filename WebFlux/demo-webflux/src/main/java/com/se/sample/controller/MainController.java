package com.se.sample.controller;

import com.se.sample.model.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/controller")
public class MainController {

    @GetMapping
    public Flux<Message> list(@RequestParam(defaultValue = "0") Long start,
                              @RequestParam(defaultValue = "2") Long count
    ) {
        Flux<Message> data = Flux
                .just("Hello Reactive!",
                        "More than one",
                        "Third post",
                        "Fourth post",
                        "Fifth post"

                )
                .skip(start)
                .take(count)
                .map(Message::new);

        return data;
    }
}
