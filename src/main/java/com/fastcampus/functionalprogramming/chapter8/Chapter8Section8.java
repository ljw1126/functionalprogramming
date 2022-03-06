package com.fastcampus.functionalprogramming.chapter8;

import com.fastcampus.functionalprogramming.chapter7.model.User;
import com.fastcampus.functionalprogramming.chapter8.service.EmailService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Partitioning By
 * - Group by 와 거의 비슷한데 매개변수가 Predicate(조건)를 받아서 true/false 그룹 나뉨
 */
public class Chapter8Section8 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(13,2,101,203,304,402,305,349,2312,203);
        //짝수 , 홀수 그룹
        Map<Boolean, List<Integer>> numberPartions = numbers.stream()
                .collect(Collectors.partitioningBy(number -> number %2 == 0 ));
        System.out.println("Odd(홀수) number group : " + numberPartions.get(false));
        System.out.println("Even(짝수) number group : " + numberPartions.get(true));


        User user1 = new User()
                .setId(101)
                .setName("Aaul")
                .setVerified(true)
                .setEmailAddress("alice@fastcampus.co.kr")
                .setFriendUserIds(Arrays.asList(201,202,203,204,205,206));

        User user2 = new User()
                .setId(102)
                .setName("David")
                .setVerified(false)
                .setEmailAddress("bob@fastcampus.co.kr")
                .setFriendUserIds(Arrays.asList(204,205,206));

        User user3 = new User()
                .setId(103)
                .setName("John")
                .setVerified(false)
                .setEmailAddress("charlie@fastcampus.co.kr")
                .setFriendUserIds(Arrays.asList(204,205,207));

        List<User> users = Arrays.asList(user1,user2,user3);

        // 친구 수가 5명 초과면 친구랑 놀아라, 5명이하면 친구를 더 늘려라!
        Map<Boolean, List<User>> userPartitions = users.stream()
                .collect(Collectors.partitioningBy(user -> user.getFriendUserIds().size() > 5));

        EmailService emailService = new EmailService();
        /*for(User user : userPartitions.get(true)){
            emailService.sendPlayWithFriendsEmail(user);
        }
        System.out.println("==================================");
        for(User user : userPartitions.get(false)){
            emailService.sendMakeMoreFriendsEmail(user);
        }*/
        System.out.println("==================================");

        userPartitions.get(true).forEach(emailService::sendPlayWithFriendsEmail);
        userPartitions.get(false).forEach(emailService::sendMakeMoreFriendsEmail);
    }
}
