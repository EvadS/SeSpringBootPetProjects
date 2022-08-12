package org.se.sample;

import java.util.*;

public class ComparableDemo {

    public static void main(String[] args) {

        Person p1 = new Person("name 1", 14);
        Person p2 = new Person("name 2", 67);
        Person p3 = new Person("name 3", 18);
        Person p4 = new Person("name 4", 50);

        List<Person> list = new ArrayList<>();
        //treeSet.addAll(Arrays.asList(p1,p2,p3));
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);

        System.out.println("-------до сортировки--------");
        list.stream().forEach(System.out::println);
        System.out.println("-------после сортировки-----");
        list.stream().forEach(System.out::println);
        Collections.sort(list);
    }
}
