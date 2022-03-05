package com.fastcampus.functionalprogramming.chapter8;

import com.fastcampus.functionalprogramming.chapter6.model.Order;
import com.fastcampus.functionalprogramming.chapter6.model.User;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 8.2 All Match Any Match
 */
public class Chapter8Section2 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3,-4,2,7,9);

        //ex1. 모두가 양수인가?
        boolean allPositive = numbers.stream()
                                      .allMatch(number -> number > 0);
        System.out.println(allPositive);

        //ex2. 하나라도 음수가 있는가?
        boolean anyNegative = numbers.stream()
                .anyMatch(number -> number <0);
        System.out.println(anyNegative);

        User user1 = new User()
                .setId(101)
                .setName("Aaul")
                .setVerified(true)
                .setEmailAddress("alice@fastcampus.co.kr");

        User user2 = new User()
                .setId(102)
                .setName("David")
                .setVerified(false)
                .setEmailAddress("bob@fastcampus.co.kr");

        User user3 = new User()
                .setId(103)
                .setName("John")
                .setVerified(false)
                .setEmailAddress("charlie@fastcampus.co.kr");

        List<User> users = Arrays.asList(user1, user2, user3);

        // ex3. 유저가 전부 검증이 되었는가?
        boolean areAllUserVerified = users.stream().allMatch(User::isVerified);
        System.out.println(areAllUserVerified);

        Order order1 = new Order()
                .setId(1001L)
                .setAmount(BigDecimal.valueOf(2000))
                .setStatus(Order.OrderStatus.CREATED);

        Order order2 = new Order()
                .setId(1002L)
                .setAmount(BigDecimal.valueOf(4000))
                .setStatus(Order.OrderStatus.ERROR);

        Order order3 = new Order()
                .setId(1003L)
                .setAmount(BigDecimal.valueOf(3000))
                .setStatus(Order.OrderStatus.ERROR);

        Order order4 = new Order()
                .setId(1004L)
                .setAmount(BigDecimal.valueOf(7000))
                .setStatus(Order.OrderStatus.PROCESSED);

        List<Order> orders = Arrays.asList(order1,order2,order3,order4);

        //TODO. check if any of orders is in ERROR status
        //에러 상태가 하나라도 있는지 확인
        //isAnyOrderInErrorStatus
        boolean existErrorOrder = orders.stream()
                                        .anyMatch(order -> order.getStatus() == Order.OrderStatus.ERROR);

        System.out.println(existErrorOrder);
    }
}
