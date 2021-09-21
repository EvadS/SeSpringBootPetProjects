package com.se.sample;

import com.se.sample.model.People;
import com.se.sample.model.UserResponse;
import com.se.sample.model.enums.Sex;
import com.se.sample.service.UserService;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class DemoWebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoWebfluxApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            //acticle_1_demo();
//            acticle_2_demo();

            //demo3();
          //  demo4();
           //doFinalyTest();

            testMapper();
        };
    }

    @Autowired
    UserService userService;

    private void testMapper(){
        Flux<UserResponse> usersInfo = userService.getUsersInfo();

        usersInfo.blockFirst();
        int a=0;
    }
    private void doFinalyTest() {
        System.out.println(checkOnErrorMethodTryCatchFinally());


    }

    private void demo4() {
        //to join publishers
        Flux.just("a", "b", "c")
                .zipWith(Flux.just(1, 2, 3), (word, number) -> word + number)
                .subscribe(System.out::println);
    }

    private void demo3() {
        Collection<People> peoples = Arrays.asList(
                new People("Vasya", 16, Sex.MAN),
                new People("Petr", 23, Sex.MAN),
                new People("Elena", 42, Sex.WOMEN),
                new People("Ivan", 69, Sex.MAN)
        );

        Flux.fromIterable(peoples)
                .filter((s) -> s.getSex() == Sex.MAN)
                .map(People::getAge)
                .reduce(Integer::max)
                .subscribe(System.out::println);

        int a=0;


    }

    private void acticle_2_demo() {

        System.out.println("=== ARTICLE 2 ===========");

        System.out.println("== a2, map ");
        System.out.println("subscribe ");
        Flux.just(1, 2, 3)
                .map(s -> s + 1)

                .subscribe(System.out::println) ;


        System.out.println("collect list ");
        Mono<List<Integer>> listMono = Flux.just(1, 2, 3)
                .map(s -> s + 1)
                .collectList();

        listMono.subscribe(System.out::println);
        System.out.println("== a2, map ");

        //flatMap — асинхронно преобразует элементы, испускаемые этим потоком, в Publisher-ы, а з

//        System.out.println("== a2, flat map ");
//        Flux.range(1, 10)
//                .flatMap(v -> {
//                    if (v < 5) {
//                        return Flux.just(v * v);
//                    }
//                    return Flux.<Integer>error(new IOException("Error: "));
//                })
//                .subscribe(System.out::println, Throwable::printStackTrace);

        System.out.println("=== a2 Demo 2");

        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f");

        //flat map -> mono
        Mono<List<String>> collect = Flux.fromIterable(list)
                .flatMap(s -> {
                    return Flux.just(s + "x");
                })
                .collect(Collectors.toList());

        // flat map flux
        Flux<List<String>> flux = Flux.fromIterable(list)
                .flatMap(s -> {
                    return Flux.just(s + "x");
                })
                .collect(Collectors.toList())
                .flux();


        System.out.println("=== here ==============");

        System.out.println("flux some mapping");
        Flux<List<String>> flux2 = Flux.fromIterable(list)
                .flatMap(s -> {
                        //    return s.toUpperCase();

                    return Flux.just(s + "x");
                })
                .collect(Collectors.toList())
                .flux();

    }

    private void acticle_1_demo() {
        Flux flux1 = Flux.just("foo", "bar", "foobar");
        Flux flux2 = Flux.fromIterable(Arrays.asList("A", "B", "C"));
        Flux flux3 = Flux.range(5, 3);


        Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .limitRequest(5)
                .skip(3)
                .subscribe(value -> System.out.println("Value: " + value));

        List<Integer> elements = new ArrayList<>();

        System.out.println("-------- complete//subscribe ");
        Flux.just(1, 2, 3, 4)
                .doOnComplete(() -> {
                    System.out.println("** Completed");
                })
                .subscribe(System.out::println);

        System.out.println("====Step 2");

        Flux.just(1, 2, 3, 4)
               .map(i-> String.valueOf(i*100/2))
                .subscribe(System.out::println);


        System.out.println("====Step 3");
        List<Integer> elements2 = new ArrayList<>();
        Flux.just(1, 2, 3, 4)
                .log()
                .subscribe(elements2::add);

        System.out.println("==== Step 4 ");

        Flux.just("1", "2", "3", "4")
                .subscribe(value -> System.out.println("Value: " + value));

        System.out.println("==== Step 5, OnErorr");

        Flux.just(1, 2, 3, 4, 5)
                .subscribe(value -> {
                    if (value > 4) {
                        throw new IllegalArgumentException(value + " > than 4");
                    }
                    System.out.println("Value: " + value);
                }, error -> System.out.println("Error: " + error.getMessage()));


        Flux<Integer> ints = Flux.range(1, 5)
                .map(i -> {
                    if (i <= 3) return i;
                    throw new RuntimeException("Got to 4");
                });

        int b=0;
        ints.subscribe(System.out::println,
                error -> System.err.println("Error map: " + error));

        //Subscriber demo
        extracted(elements);
    }

    private void extracted(List<Integer> elements) {
        Flux.just(1, 2, 3, 4)
                .log()
                                   .subscribe(new Subscriber<Integer>() {
                    private Subscription s;
                    int onNextAmount;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.s = s;
                        s.request(2);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        elements.add(integer);
                        onNextAmount++;
                        if (onNextAmount % 2 == 0) {
                            s.request(2);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {}

                    @Override
                    public void onComplete() {}
                });
    }

    /**
     * аналог try catch
     * @return
     */
    private Flux<Integer> checkOnErrorMethodTryCatchFinally() {
        Flux.just(1, 2, 3)
                .map(this::testNumbers)
                .doFinally(signalType -> {
                    if (signalType == SignalType.ON_ERROR) {
                        System.out.println("Error signal");
                    } else if (signalType == SignalType.CANCEL) {
                        System.out.println("Cancel signal");
                    }
                })
                .subscribe(value -> System.out.println("Value: " + value),
                        error -> System.out.println("Error: " +
                                error.getMessage()));
        return null;
    }


    private int testNumbers(int value) {
        if (value > 2) {
            throw new IllegalArgumentException("value is too high!");
        }
        return value;
    }
}


