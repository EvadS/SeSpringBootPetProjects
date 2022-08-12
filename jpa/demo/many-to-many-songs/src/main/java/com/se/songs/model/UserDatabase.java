package com.se.songs.model;

import com.se.songs.entity.User;
import com.se.songs.enums.Role;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {

    public List<User> getAllUser() {

        List<User> users = new ArrayList<>();

        User u1 = new User("Elton Winsley", Role.STAFF);
        User u2 = new User("Bruce Lee", Role.CUSTOMER);
        User u3 = new User("Markus Sad", Role.STAFF_CHIEF);
        User u4 = new User("Kimi Jones", Role.TRAINEE_REGULAR);
        User u5 = new User("Franklin Bug", Role.TRAINEE_SCHOOL);

        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        users.add(u5);

        return users;
    }
}