package com.se.sample.repository;

import com.se.sample.model.UserDao;
import com.se.sample.model.enums.Sex;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Collection;

public class UserRepo {

    static Collection<UserDao> peoples;

    static
    {
        peoples = Arrays.asList(
                new UserDao(1, "Vasya", Sex.MAN.getId()),
                new UserDao(2, "Petr", Sex.MAN.getId()),
                new UserDao(3, "Elena", Sex.WOMEN.getId()),
                new UserDao(4, "Ivan", Sex.MAN.getId())
        );
    }

    public static Flux<UserDao> getUserList() {
        return Flux.fromIterable(peoples);
    }
}
