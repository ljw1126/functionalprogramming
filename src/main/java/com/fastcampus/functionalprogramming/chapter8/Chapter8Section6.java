package com.fastcampus.functionalprogramming.chapter8;

import com.fastcampus.functionalprogramming.chapter6.model.Order;
import com.fastcampus.functionalprogramming.chapter6.model.OrderLine;
import com.fastcampus.functionalprogramming.chapter6.model.User;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 8.6 To Map
 * 가장 많이, 자주 사용한다 함 !
 */
public class Chapter8Section6 {
    public static void main(String[] args) {
        Map<Integer,String> numberMap = Stream.of(3,5,-4,2,6)
                                             .collect(Collectors.toMap(Function.identity(), x->"Number is "+x));
        //toMap안에 x->x은 Function.identity() 로도 사용가능 !!
        System.out.println(numberMap);

        User user1 = new User()
                .setId(101)
                .setName("Aaul")
                .setVerified(true)
                .setEmailAddress("alice@fastcampus.co.kr")
                .setFriendUserIds(Arrays.asList(201,202,203,204));

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
        // id to Object 로 만든다는데(자주 사용)
        Map<Integer,User> userIdToUserMap = users.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));
        System.out.println(userIdToUserMap);

        //직접 해보기
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
                .setAmount(BigDecimal.valueOf(7000))
                .setStatus(Order.OrderStatus.PROCESSED);

        List<Order> orders = Arrays.asList(order1,order2,order3);
        // TODO : Create a map from order id to order status
        Map<Long, Order.OrderStatus> orderIdToOrderStatusMap = orders.stream()
                                                                     .collect(Collectors.toMap(Order::getId,Order::getStatus));
        System.out.println(orderIdToOrderStatusMap);
        System.out.println(orderIdToOrderStatusMap.get(1003L));
    }
}
