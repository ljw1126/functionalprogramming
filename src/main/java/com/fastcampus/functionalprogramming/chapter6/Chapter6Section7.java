package com.fastcampus.functionalprogramming.chapter6;

import com.fastcampus.functionalprogramming.chapter6.model.Order;
import com.fastcampus.functionalprogramming.chapter6.model.OrderLine;
import com.fastcampus.functionalprogramming.chapter6.model.OrderLine.OrderLineType;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * TODO : 다시 보기 ( 효율성 높음 ** 꼭 복습!! )
 * FlatMpa = Map + Flatten
 * 데이터에 함수를 적용한 후 중첩된 stream을 연결하여 하나의 stream의 리턴
 * T를 입력받아 R 타입의 stream을 return 한다는데.. ?
 */
public class Chapter6Section7 {
    public static void main(String[] args) {
        String[][] cities = new String[][]{
                {"Seoul", "Busan"},
                {"San Francisco", "New York"},
                {"Madrid", "Barcelona"}
        };
        Stream<String[]> cityStream = Arrays.stream(cities);
        Stream<Stream<String>> cityStreamStream = cityStream.map(x -> Arrays.stream(x));
        List<Stream<String>> cityStreamList = cityStreamStream.collect(Collectors.toList()); // 출력 x

        Stream<String[]> cityStream2 = Arrays.stream(cities);
        Stream<String> flattenedCityStream = cityStream2.flatMap(x -> Arrays.stream(x));     // Array 를 stream 형태로
        List<String> flattenedCityList = flattenedCityStream.collect(Collectors.toList());
        System.out.println(flattenedCityList); // [Seoul, Busan, San Francisco, New York, Madrid, Barcelona]

        Order order1 = new Order().setId(1001)
                .setOrderLines(Arrays.asList(
                        new OrderLine().setId(10001)
                                .setType(OrderLineType.PURCHASE)
                                .setAmount(BigDecimal.valueOf(5000)),
                        new OrderLine().setId(10002)
                                .setType(OrderLineType.PURCHASE)
                                .setAmount(BigDecimal.valueOf(4000))
                ));

        Order order2 = new Order()
                .setId(1002)
                .setOrderLines(Arrays.asList(
                        new OrderLine().setId(10003)
                                .setType(OrderLineType.PURCHASE)
                                .setAmount(BigDecimal.valueOf(2000)),
                        new OrderLine().setId(10004)
                                .setType(OrderLineType.DISCOUNT)
                                .setAmount(BigDecimal.valueOf(-1000))
                ));

        Order order3 = new Order()
                .setId(1003)
                .setOrderLines(Arrays.asList(
                        new OrderLine().setId(10005)
                                .setType(OrderLineType.PURCHASE)
                                .setAmount(BigDecimal.valueOf(2000))
                ));

        // 각 Order 안에 List<OrderLine>을 합쳐서 하나로 만듦
        List<Order> orders = Arrays.asList(order1, order2, order3);
        List<OrderLine> mergeOrderLines = orders.stream()    // Stream<Order>
                .map(Order::getOrderLines)    //Stream<List<OrderLine>>
                .flatMap(List::stream)      //Stream<OrderLine>
                .collect(Collectors.toList());
        System.out.println(mergeOrderLines);

        /**
         * 결과 - 하나의 List안에 OrderLine 다 뽑음
         * [OrderLine{id=10001, type=PURCHASE, productId=0, quantity=0, amount=5000},
         * OrderLine{id=10002, type=PURCHASE, productId=0, quantity=0, amount=4000},
         * OrderLine{id=10003, type=PURCHASE, productId=0, quantity=0, amount=2000},
         * OrderLine{id=10004, type=DISCOUNT, productId=0, quantity=0, amount=-1000},
         * OrderLine{id=10005, type=PURCHASE, productId=0, quantity=0, amount=2000}]
         */
    }
}
