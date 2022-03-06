package com.fastcampus.functionalprogramming.chapter8;

import com.fastcampus.functionalprogramming.chapter7.model.User;
import com.fastcampus.functionalprogramming.chapter8.service.EmailService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 8.10 Parallel(병렬) Stream
 * - 명확한 장단점 존재함
 */
public class Chapter8Section10 {
    public static void main(String[] args) {
        // 검증되지 않는 유저를 선별해서 메일 보내는 것 <= 병렬 처리하기 좋다
        User user1 = new User()
                .setId(101)
                .setName("Alicce")
                .setVerified(true)
                .setEmailAddress("alice@fastcampus.co.kr");

        User user2 = new User()
                .setId(102)
                .setName("David")
                .setVerified(false)
                .setEmailAddress("David@fastcampus.co.kr")
                .setFriendUserIds(Arrays.asList(204,205,206));

        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setVerified(false)
                .setEmailAddress("charlie@fastcampus.co.kr");


        User user4 = new User()
                .setId(104)
                .setName("Bob")
                .setVerified(true)
                .setEmailAddress("bob@fastcampus.co.kr");

        User user5 = new User()
                .setId(105)
                .setName("John")
                .setVerified(false)
                .setEmailAddress("john@fastcampus.co.kr")
                .setFriendUserIds(Arrays.asList(204,205,206));

        User user6 = new User()
                .setId(106)
                .setName("May")
                .setVerified(false)
                .setEmailAddress("may@fastcampus.co.kr");

        List<User> users = Arrays.asList(user1,user2,user3,user4,user5,user6);
        EmailService emailService = new EmailService();

        long startTime = System.currentTimeMillis();
        users.stream()
             .filter(user->!user.isVerified())
             .forEach(emailService::sendVerifyYourEmailEmail);
        long endTime = System.currentTimeMillis();
        System.out.println("Sequential (순차처리):" + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        users.stream().parallel()
                .filter(user->!user.isVerified())
                .forEach(emailService::sendVerifyYourEmailEmail);
        endTime = System.currentTimeMillis();
        System.out.println("Parallel (병렬처리):" + (endTime - startTime) + "ms");
        // 이메일 날라가는 순서가 뒤죽 박죽인데 .. 상관없는 경우 좀더 병렬처리가 속도 빠름(순서가 필요없는 상황에 사용하는게 좋음)
        // 단, 순차처리 필요한 경우 스레드 처리는 뒤죽박죽이지만, 마지막에 모아주면 됨(?)
        System.out.println("==============================");
        List<User> processedUsers = users.parallelStream()
                .map(user -> {
                    System.out.println("Capitalize user name for user " + user.getId());
                    user.setName(user.getName().toUpperCase());
                    return user;
                })
                .map(user->{
                    System.out.println("Set 'isVerified' to true for user " + user.getId());
                    user.setVerified(true);
                    return user;
                }).collect(Collectors.toList());
        System.out.println(processedUsers);
    }
}
