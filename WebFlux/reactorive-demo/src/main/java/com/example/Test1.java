package com.example;

import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test1 {
    public static void main(String[] args) {
        Stream.of(1,2,3,4)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        final CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                //...long running...
                return "42";
            }
        }, executorService);
    }
}
