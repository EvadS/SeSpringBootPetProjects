package com.se.se.sample.group.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class WorkerHalper {


    private static List<Worker> workers = new ArrayList();
    /**
     * Группировка списка рабочих по их должности (деление на списки)
     */
    public static void test1() {
    Map<String, List<Worker>> map1 = workers.stream()
            .collect(Collectors.groupingBy(Worker::getPosition));

}

    /**
     * Группировка списка рабочих по их должности (деление на множества)
     */
     public static void test2() {
         Map<String, Set<Worker>> map2 = workers.stream()
                 .collect(Collectors.groupingBy(Worker::getPosition, Collectors.toSet()));

     }


    /**
     * Подсчет количества рабочих, занимаемых конкретную должность
     */
    public static void test3() {
        Map<String, Long> map3 = workers.stream()
                .collect(Collectors.groupingBy(Worker::getPosition, Collectors.counting()));
    }

    /**
     * Группировка списка рабочих по их должности, при этом нас интересуют только имена
     */
    public static void test4() {
        Map<String, Set<String>> map4 = workers.stream()
                .collect(Collectors.groupingBy(Worker::getPosition,
                        Collectors.mapping(Worker::getName, Collectors.toSet())));
    }


    /**
     * 5. Расчет средней зарплаты для данной должности
     */
    public static void test5() {
        Map<String, Double> map5 = workers.stream()
                .collect(Collectors.groupingBy(Worker::getPosition,
                        Collectors.averagingInt(Worker::getSalary)));
    }

    /**
     * . Группировка списка рабочих по их должности, рабочие представлены только именами единой строкой
     */
    public static void test6() {
        Map<String, String> map6 = workers.stream()
                .collect(Collectors.groupingBy(Worker::getPosition,
                        Collectors.mapping(Worker::getName,
                                Collectors.joining(", ", "{","}")))
                );
    }

    /**
     * Группировка списка рабочих по их должности и по возрасту.
     */
    public static void test7() {
        Map<String, Map<Integer, List<Worker>>> collect = workers.stream()
                .collect(Collectors.groupingBy(Worker::getPosition,
                        Collectors.groupingBy(Worker::getAge)));
    }





}
