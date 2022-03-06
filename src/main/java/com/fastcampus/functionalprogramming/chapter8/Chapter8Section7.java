package com.fastcampus.functionalprogramming.chapter8;

import com.fastcampus.functionalprogramming.chapter6.model.Order;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 8.7 Grouping by
 * - Stream 안의 데이터에 Function<? super T, ? extends K>classifer를 적용했을대 결과값이 같은 값끼리
 *   List 로 모아서 Map의 형태로 반환해주는 collector
 *   이때 key는 classifer의 결과값 , value는 그 결과값을 갖는 데이터들
 *   (예시는 강의 참고)
 * - 두번째 매개변수로 downstream collector를 넘기는 것도 가능
 *   - 이 경우 List대신 collector를 적용시킨 값으로 map의 value가 만들어짐
 *   - 이 때 자주 쓰이는 것이 mapping / reducing 등의 collector
 */
public class Chapter8Section7 {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(13,2,101,203,304,402,305,349,2312,203);
        // 나머지 값 별로 그룹
        Map<Integer, List<Integer>> unitDigitMap = numbers.stream()
                .collect(Collectors.groupingBy(number -> number %10));
        System.out.println(unitDigitMap);

        // 중복값 제외한 나머지 값 별로 그룹
        Map<Integer, Set<Integer>> unitDigitSet = numbers.stream()
                .collect(Collectors.groupingBy(number -> number % 10 , Collectors.toSet()));
        System.out.println(unitDigitSet);

        // number % 10 값으로 그룹을 하고 , mapping 에서는 해당 String 형태의 List로 처리
        Map<Integer, List<String>> unitDigitStrMap = numbers.stream()
                .collect(Collectors.groupingBy(number -> number % 10,
                        Collectors.mapping(number-> "unit digit is " + number, Collectors.toList())));
        System.out.println(unitDigitStrMap);
        System.out.println(unitDigitStrMap.get(3));


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
        //TODO: create a map from order status to the list of corresponding orders
        //OrderStatus를 키로 해서 group by
        Map<Order.OrderStatus, List<Order>> orderStatusMap = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus));
        System.out.println(orderStatusMap);

        //OrderStatus가 키이고 List의 amount 합을 value로 하는 map
        Map<Order.OrderStatus, BigDecimal> orderStatusToSumOfAmountMap = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus,
                        Collectors.mapping(Order::getAmount, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
        System.out.println(orderStatusToSumOfAmountMap);
    }
}
