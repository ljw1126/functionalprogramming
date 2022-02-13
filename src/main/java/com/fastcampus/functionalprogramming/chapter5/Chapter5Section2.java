package com.fastcampus.functionalprogramming.chapter5;

import com.fastcampus.functionalprogramming.chapter5.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.BiPredicate;

public class Chapter5Section2 {

    public static void main(String[] args) {
        Function<String, Integer> strLength = String::length;
        System.out.println(strLength.apply("hello world!"));

        BiPredicate<String, String> strEquals = String::equals;
        System.out.println(strEquals.test("hello","world"));
        System.out.println(strEquals.test("hello","hello"));

        List<User> users = new ArrayList<>();
        users.add(new User(3, "alice"));
        users.add(new User(1, "charlie"));
        users.add(new User(2, "bob"));

        //printUserField(users, (User user) -> user.getId());
        printUserField(users, User::getId);
        printUserField(users, User::getName);
    }

    // helper method
    public static void printUserField(List<User> users, Function<User,Object> getter){
        for(User user : users)
            System.out.println(getter.apply(user));
    }
}
