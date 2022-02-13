package com.fastcampus.functionalprogramming.chapter6;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fastcampus.functionalprogramming.chapter6.model.User;
import com.fastcampus.functionalprogramming.chapter6.model.Order;

/**
 * TODO : map() 이해 하기
 */
public class Chapter6Section3 {
    public static void main(String[] args) {
        List<Integer> numberList = Arrays.asList(3,6,-4);

        //Stream<Integer> numberStream = numberList.stream();
        //Stream<Integer> numberStreamX2 = numberStream.map(x->x*2);
        //List<Integer> numberListX2 = numberStreamX2.collect(Collectors.toList());
        //System.out.println(numberListX2);
        //한 줄 요약
        List<Integer> numberStream = numberList.stream()
                                               .map(x->x*2)
                                               .collect(Collectors.toList());
        //input은 Integer인데 output은 String
        Stream<Integer> numberListStream = numberList.stream();
        Stream<String> strStrema = numberListStream.map(x -> "Number is "+ x);
        List<String> strList = strStrema.collect(Collectors.toList());
        System.out.println(strList);

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
        /*Stream<User> userStream = users.stream();
        Stream<String> emailStream = userStream.map(User::getEmailAddress); // 이메일만 뽑음
        List<String> emailList = emailStream.collect(Collectors.toList());
        System.out.println(emailList);*/

        List<String> userStream = users.stream()
                                     .map(User::getEmailAddress).collect(Collectors.toList());
        System.out.println(userStream);

        //ex2.
        Order order1 = new Order()
                .setId(1001)
                .setStatus(Order.OrderStatus.CREATED)
                .setCreatedByUserId(101);
        Order order2 = new Order()
                .setId(1002)
                .setStatus(Order.OrderStatus.ERROR)
                .setCreatedByUserId(103);
        Order order3 = new Order()
                .setId(1003)
                .setStatus(Order.OrderStatus.PROCESSED)
                .setCreatedByUserId(102);
        Order order4 = new Order()
                .setId(1004)
                .setStatus(Order.OrderStatus.ERROR)
                .setCreatedByUserId(104);
        Order order5 = new Order()
                .setId(1005)
                .setStatus(Order.OrderStatus.IN_PROGRESS)
                .setCreatedByUserId(101);

        List<Order> orders = Arrays.asList(order1,order2,order3,order4,order5);
        // TODO : Create list of createdByUserId (createdByUserId 만 추출한 리스트)
        List<Long> createdByUserIds = orders.stream()
                                            .map(Order::getCreatedByUserId)
                                            .collect(Collectors.toList());
        System.out.println(createdByUserIds);

    }
}
