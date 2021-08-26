package org.se.sample;

import java.util.Optional;

public class OptionalApp {
    public static void main(String[] args) {
        createOptionals();
        getFromOptional();

        isPresentTest();
        ifPresentTest();

    }

    /**
     * ifPresent/ orElse / orElseGet /orElseThrow
     */
    private static void ifPresentTest() {
        /*
            Optional.ifPresent выполняет переданное действие, если значение в Optional присутствует,
            иначе игнорирует его. Метод принимает лямбда-выражение известное как потребитель (Consumer).
         */
        Optional<String> name = Optional.of("John");
        name.ifPresent(val -> System.out.println(val)); //условие выполнится и мы увидим John

        Optional<Object> empty = Optional.empty();
        empty.ifPresent(val -> System.out.println(val)); //условие не выполнится, действие игнорируется

        //Метод Optional.orElse возвращает переданное значение, если Optional пустой
        name = Optional.of("John");
        System.out.println(name.orElse("blank")); //output John

        empty = Optional.empty();
        System.out.println(empty.orElse("blank")); //output blank

        name = Optional.of("John");
        System.out.println(name.orElseGet(() -> "blank")); //output John

        empty = Optional.empty();
        System.out.println(empty.orElseGet(() -> "blank")); //output blank

        //orElseThrow бросает переданное исключение , если Optional пустой
        name = Optional.of("John");
        String nameValue = name.orElseThrow(() -> new RuntimeException());
        System.out.println(nameValue);                                      //output John
        try {
            empty = Optional.empty();
            Object emptyValue = empty.orElseThrow(() -> new RuntimeException()); //java.lang.RuntimeException
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Optional.isPresent возвращает true, если значение в нем присутствует, иначе возвращает false
     */
    private static void isPresentTest() {
        // Optional.isPresent возвращает true, если значение в нем присутствует, иначе возвращает false
        Optional<String> name = Optional.of("John");
        if (name.isPresent()) {             //условие выполнится и мы увидим имя
            System.out.println(name.get()); //output John
        }

        Optional<Object> empty = Optional.empty();
        if (empty.isPresent()) {            //условие не выполнится, значит исключения не будет
            System.out.println(empty.get());
        }
    }

    private static void getFromOptional() {
        Optional<String> name = Optional.of("John");
        System.out.println(name.get()); //output John

        try {
            Optional<Object> nullOptional = Optional.ofNullable(null);
            System.out.println(nullOptional.get()); // java.util.NoSuchElementException: No value present
        } catch (Exception e) {
            System.out.println("java.util.NoSuchElementException: No value present");
            System.out.println("ex " + e.getMessage());
        }
    }

    private static void createOptionals() {
        //Optional.of
        Optional<String> name = Optional.of("John");
        System.out.println(name); //output Optional[John]

        // нельзя передавать null
        try {
            name = Optional.of(null); // java.lang.NullPointerException
            System.out.println(name);
        } catch (Exception ex) {
            System.out.println("ex: " + ex.getMessage());
            System.out.println("null cannot be passed");
        }

        name = Optional.ofNullable("John");
        System.out.println(name); //output Optional[John]

        //в метод Optional.ofNullable передавать null можно безопасно
        Optional<String> emptyOptional = Optional.empty();
        System.out.println(emptyOptional); //output Optional.empty

    }
}
