package com.se.sample;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class DemoLogs {
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();

        Flux.range(1, 5)
                .checkpoint("Observed error on processFoo", true)
                .log()
                .subscribe(i -> intList.add(i));

        System.out.println("------------------------");

        System.out.println("------------------------");
        List<String> strList = new ArrayList<>();

        Flux.fromArray(new String[]{"q","w","e","r","t"} )
                .log("Category")
                .subscribe(strList::add);

        Flux<List<String>> flux = Mono.just(strList).flux();

    }
}
