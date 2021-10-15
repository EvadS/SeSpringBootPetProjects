package io.pivotal.literx;

import java.util.function.*;

public class FunctionalsDemo {

    public  void test (){
        //базовые функциональные интерфейсы
        UnaryOperator<Integer> unaryOperator;
        BinaryOperator<Integer> binaryOperator;

        Function<Integer, String> function;
        Predicate<Integer> predicate;
        Consumer consumer;
        Supplier<Long> supplier;
    }


    private static void testFunctionInterface() {
        Function<Integer, String > intToStrFunction = (x) ->{
            return String.valueOf(x);
        };

        String val = intToStrFunction.apply(100);

        System.out.println("val: " +val);
    }


    public static void main(String[] args) {
            testFunctionInterface();
        testFunctionInterface2();
    }

    private static void testFunctionInterface2() {

        Function<Long, String > funct = new Function<Long, String>() {
            @Override
            public String apply(Long aLong) {
                return String.valueOf(aLong);
            }
        };

        String apply = funct.apply(1000L);
        System.out.println("res: "+ apply);

        // лямбда-выражения являются в некотором роде сокращенной формой внутренних анонимных классов
        /// Создание лямбда-выражения:
        funct = longValue -> String.valueOf(longValue);

        apply = funct.apply(2000L);
        System.out.println("res: "+ apply);
    }
}
