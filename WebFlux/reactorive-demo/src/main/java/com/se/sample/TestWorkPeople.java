package com.se.sample;

import com.se.sample.model.People;
import com.se.sample.model.Sex;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Collection;

public class TestWorkPeople {

        public static void main(String[] args) {

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
        }
}
