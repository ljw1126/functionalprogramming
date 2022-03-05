package com.fastcampus.functionalprogramming.chapter8;

import com.fastcampus.functionalprogramming.chapter6.model.Order;
import com.fastcampus.functionalprogramming.chapter6.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 8.1 Max Min Count
 */
public class Chapter8Section1 {
    public static void main(String[] args) {
        // ex1. 제일 큰 값
        Optional<Integer> max = Stream.of(5,3,6,2,1)
                                      .max(Integer::compareTo);
                                      //.max((x,y)->x-y);
        System.out.println(max.get());

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
        //ex2. 제일 첫 이름 가진 객체
        User firstUser = users.stream()
                        .min((u1,u2)->u1.getName().compareTo(u2.getName()))
                        .get();
        System.out.println(firstUser);

        //ex3. 양수 카운트
        long positiveIntegerCount = Stream.of(1,-4,5,-3,6)
                .filter(x-> x > 0)
                .count();
        System.out.println(positiveIntegerCount);

        //ex.4 createdAt 필드 생성
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        user1.setCreatedAt(now.minusDays(2));//현재부터 2일전
        user2.setCreatedAt(now.minusHours(10));
        user3.setCreatedAt(now.minusHours(1));

        User user4 = new User()
                        .setId(104)
                        .setName("Alice")
                        .setVerified(false)
                        .setEmailAddress("Alice@fastcampus.co.kr")
                        .setCreatedAt(now.minusHours(27));

        users = Arrays.asList(user1,user2,user3,user4);

        // 하루(24시간) 전 이후 검증되지 않은 유저 수
        long unverifiedUsersIn24Hrs = users.stream()
                                           .filter(user -> user.getCreatedAt().isAfter(now.minusDays(1)))
                                           .filter(user -> !user.isVerified())
                                           .count();
        System.out.println(unverifiedUsersIn24Hrs);

        //TODO: find order with highest amount an in ERROR status
        //에러 상태 order 중에서 amount가 가장 비싼 주문을 찾아서 출력하기
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
        //errorOrderWithMaxAmount
        Order maxOrderInError = orders.stream()
                .filter(order -> order.getStatus() == Order.OrderStatus.ERROR)
                .max((o1,o2) -> o1.getAmount().compareTo(o2.getAmount()))
                .get();

        // BigDecimal https://www.baeldung.com/java-bigdecimal-biginteger
        // ※ compareTo와 Comparator 헷갈리네..
        System.out.println(maxOrderInError);

        // 에러상태 주문중에 가장 비싼 금액? ( 없으면 0 리턴, Error 없으면 0이 default로 리턴 )
        BigDecimal maxErrorAmount = orders.stream()
                                          .filter(order -> order.getStatus() == Order.OrderStatus.ERROR)
                                          .map(Order::getAmount)
                                          .max(BigDecimal::compareTo)
                                          .orElse(BigDecimal.ZERO);
        System.out.println(maxErrorAmount);
    }
}
