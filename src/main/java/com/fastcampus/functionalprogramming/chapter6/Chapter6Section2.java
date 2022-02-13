package com.fastcampus.functionalprogramming.chapter6;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fastcampus.functionalprogramming.chapter6.model.Order.OrderStatus;
import com.fastcampus.functionalprogramming.chapter6.model.User;
import com.fastcampus.functionalprogramming.chapter6.model.Order;
import com.fastcampus.functionalprogramming.chapter6.model.OrderLine;
public class Chapter6Section2 {

    public static void main(String[] args) {
        Stream<Integer> numberStream = Stream.of(3,-5,7,10,-3);
        Stream<Integer> filteredNumberStream = numberStream.filter(x-> x>0);
        // 람다 표현식이 Predicate
        List<Integer> filterdNumbers = filteredNumberStream.collect(Collectors.toList());
        System.out.println(filterdNumbers);

        List<Integer> newFilteredNumbers = Stream.of(3,-5,7,10,-3)
                                                 .filter(x -> x >0)
                                                 .collect(Collectors.toList());
        System.out.println(newFilteredNumbers);
        // 실전에 가까운 예제
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
        List<User> verifiedUsers = users.stream()
                                        .filter(User::isVerified)
                                        .collect(Collectors.toList());
        //.filter(user -> user.isVerified()) 로 했던것을 위와 같은 표현식으로
        System.out.println(verifiedUsers);

        List<User> unverifiedUsers = users.stream()
                                          .filter(user -> !user.isVerified())
                                          .collect(Collectors.toList());
        System.out.println(unverifiedUsers);

        Order order1 = new Order()
                .setId(1001)
                .setStatus(OrderStatus.CREATED);
        Order order2 = new Order()
                .setId(1002)
                .setStatus(OrderStatus.ERROR);
        Order order3 = new Order()
                .setId(1003)
                .setStatus(OrderStatus.PROCESSED);
        Order order4 = new Order()
                .setId(1004)
                .setStatus(OrderStatus.ERROR);
        Order order5 = new Order()
                .setId(1005)
                .setStatus(OrderStatus.IN_PROGRESS);

        List<Order> orders = Arrays.asList(order1,order2,order3,order4,order5);
        // ex. Filter orders in ERROR state
        List<Order> errorOrders = orders.stream()
                                       .filter(order -> order.getStatus() == OrderStatus.ERROR)
                                       .collect(Collectors.toList());
        System.out.println(errorOrders);


    }

}
