package com.se.sample.controller;

import com.se.sample.model.Message;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/controller")
public class MainController {
//http://localhost:8080/controller/test?queries=firstName,asc&queries=lastName,desc
    @GetMapping("/test")
    @ResponseBody
    public String test(@RequestParam String[] queries){
        int a =0;

      //  final String[] queries = sort.split("&");
        Sort orders = parseSortQuery(queries, ",");

        return  ":  -->: "+ orders;
    }

//firstName,asc&lastName,desc


    private Sort parseSortQuery(final String[] query, String delimiter) {

        final List<Sort.Order> orders = new ArrayList<>();
        for (String q : query) {

            if (q == null) {
                continue;
            }

            final String[] parts = q.split(delimiter);
            final Sort.Direction direction = parts.length == 0 ? null : Sort.Direction.fromString(parts[parts.length - 1]);
            for (int i = 0; i < parts.length; i++) {

                if (i == parts.length - 1 && direction != null) {
                    continue;
                }

                final String property = parts[i];
                if (!StringUtils.hasText(property)) {
                    continue;
                }
                orders.add(new Sort.Order(direction, property));
            }
        }
        return orders.isEmpty() ? null :  Sort.by(orders);
    }

//    private Sort parseSortQuery(final String[] query, String delimiter) {
//
//        final List<Sort.Order> orders = new ArrayList<>();
//        for (String q : query) {
//
//            if (q == null) {
//                continue;
//            }
//
//            final String[] parts = q.split(delimiter);
//            final Sort.Direction direction = parts.length == 0 ? null : Sort.Direction.fromString(parts[parts.length - 1]);
//            for (int i = 0; i < parts.length; i++) {
//
//                if (i == parts.length - 1 && direction != null) {
//                    continue;
//                }
//
//                final String property = parts[i];
//                if (!StringUtils.hasText(property)) {
//                    continue;
//                }
//                orders.add(new Sort.Order(direction, property));
//            }
//        }
//        return orders.isEmpty() ? null :  Sort.by(orders);
//    }

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
