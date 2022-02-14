package com.fastcampus.functionalprogramming.chapter6;

import com.fastcampus.functionalprogramming.chapter6.model.Order;
import com.fastcampus.functionalprogramming.chapter6.model.Order.OrderStatus;
import com.fastcampus.functionalprogramming.chapter6.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.time.LocalDateTime;
import java.time.ZoneId;
/**
 * TODO: 다시보기(sleep..)
 */
public class Chapter6Section4 {
    public static void main(String[] args) {

        //ex1.
        User user1 = new User()
                .setId(101)
                .setName("alice")
                .setVerified(true)
                .setEmailAddress("alice@fastcampus.co.kr");

        User user2 = new User()
                .setId(102)
                .setName("bob")
                .setVerified(false)
                .setEmailAddress("bob@fastcampus.co.kr");

        User user3 = new User()
                .setId(103)
                .setName("charlie")
                .setVerified(false)
                .setEmailAddress("charlie@fastcampus.co.kr");


        List<User> users = Arrays.asList(user1, user2, user3);
        List<String> emails = new ArrayList<>();
        // 방법 1. stream을 배우기 전
        for(User user : users){
            if(!user.isVerified()){
                String email = user.getEmailAddress();
                emails.add(email);
            }
        }
        System.out.println(emails);

        // 방법 2. 깔끔해짐.. 각 과정에서 역할이 분명
        List<String> emails2 = users.stream()
                                    .filter(user -> !user.isVerified())
                                    .map(User::getEmailAddress)
                                    .collect(Collectors.toList());
        System.out.println(emails2);

        //ex2.
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        Order order1 = new Order()
                .setId(1001)
                .setStatus(OrderStatus.CREATED)
                .setCreatedByUserId(101)
                .setCreatedAt(now.minusHours(4));
        Order order2 = new Order()
                .setId(1002)
                .setStatus(OrderStatus.ERROR)
                .setCreatedByUserId(103)
                .setCreatedAt(now.minusHours(1));
        Order order3 = new Order()
                .setId(1003)
                .setStatus(OrderStatus.PROCESSED)
                .setCreatedByUserId(102)
                .setCreatedAt(now.minusHours(36));
        Order order4 = new Order()
                .setId(1004)
                .setStatus(OrderStatus.ERROR)
                .setCreatedByUserId(104)
                .setCreatedAt(now.minusHours(25));
        Order order5 = new Order()
                .setId(1005)
                .setStatus(OrderStatus.IN_PROGRESS)
                .setCreatedByUserId(101)
                .setCreatedAt(now.minusHours(10));

        // 문1) 에러 상태인 Order에 User를 추출해서List로 반환하기
        // TODO : Find orders in Error status, and extract createdByUserIds as a list
        List<Order> orders = Arrays.asList(order1,order2,order3,order4,order5);
        List<Long> createByUserIds = orders.stream()
                                           .filter(order -> order.getStatus() == OrderStatus.ERROR)
                                           .map(Order::getCreatedByUserId)
                                           .collect(Collectors.toList());
        System.out.println(createByUserIds);

        // 문2) 에러 상태이고, 24시간 이내 만들어진 order찾기
        // TODO : Find orders in ERROR status and created within 24 hours
        List<Order> orders2 = orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.ERROR)
                .filter(order -> order.getCreatedAt().isAfter(now.minusHours(24)))
                //.filter(order -> !now.minusHours(24).isAfter(order.getCreatedAt())) //&&해도 되는데 이렇게 가능
                .collect(Collectors.toList());
        System.out.println(orders2);

    }
}
