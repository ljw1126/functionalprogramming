package com.fastcampus.functionalprogramming.chapter7;

import com.fastcampus.functionalprogramming.chapter7.model.User;

import java.util.Optional;

public class Chapter7Section2 {
    public static void main(String[] args) {
        Optional<User> maybeUser = Optional.ofNullable(maybeGetUser(true));
        maybeUser.ifPresent(user -> System.out.println(user));

        Optional<Integer> maybeId = Optional.ofNullable(maybeGetUser(false))
                .map(user -> user.getId());
        maybeId.ifPresent(System.out::println);

        String userName = Optional.ofNullable(maybeGetUser(false))
                .map(User::getName)
                .map(name -> "the name is " + name)
                .orElse("Name is empty"); // false이면 비어있으니 이게 출력

        System.out.println(userName);

        Optional<String> maybeEmail = Optional.ofNullable(maybeGetUser(true))
                                              .flatMap(User::getEmailAddress);
        //아래 객체의 이메일을 주석하면 아무것도 출력안됨
        maybeEmail.ifPresent(System.out::println);
    }

    //helper method
    public static User maybeGetUser(boolean returnUser){
        if(returnUser){
            return new User()
                    .setId(1001)
                    .setName("alice")
                    .setEmailAddress("alice@fastcampus.co.kr")
                    .setVerified(false);
        }
        return null;
    }
}
