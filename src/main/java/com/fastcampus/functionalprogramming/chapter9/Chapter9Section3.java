package com.fastcampus.functionalprogramming.chapter9;

import com.fastcampus.functionalprogramming.chapter9.model.Order;
import com.fastcampus.functionalprogramming.chapter9.model.OrderLine;
import com.fastcampus.functionalprogramming.chapter9.priceprocessor.OrderLineAggregationPriceProcessor;
import com.fastcampus.functionalprogramming.chapter9.priceprocessor.TaxPriceProcessor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Chapter9Section3 {
    public static void main(String[] args) {
        Function<Integer,Integer> multiplyByTwo = x -> 2 * x;
        Function<Integer,Integer> addTen = x -> x + 10;

        Function<Integer,Integer> composedFunction
                = multiplyByTwo.andThen(addTen);
        System.out.println(composedFunction.apply(3)); // 16

        Order unprocessedOrder = new Order()
                .setId(1001L)
                .setOrderLines(Arrays.asList(
                        new OrderLine().setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine().setAmount(BigDecimal.valueOf(2000))
                ));

        List<Function<Order,Order>> priceProcessors = getPriceProcessors(unprocessedOrder);

        // 합성된 PriceProcessor 구함
        Function<Order,Order> mergedPriceProcessors = priceProcessors.stream()
                .reduce(Function.identity(), Function::andThen);
                //.reduce(Function.identity() , (priceProcessors1, priceProcessors2) -> priceProcessors1.andThen(priceProcessors2))

        Order processedOrder = mergedPriceProcessors.apply(unprocessedOrder);
        System.out.println(processedOrder); // amount를 다 더하고, 세율 1.09375 곱하니 Order의 amount 가 3281.25000 나오는 구나
    }

    //helper 생성, Order에 성질에 따라서 이 함수가 처리하려 리턴함
    public static List<Function<Order, Order>> getPriceProcessors(Order order) {
        return Arrays.asList(new OrderLineAggregationPriceProcessor(),
                new TaxPriceProcessor(new BigDecimal("9.375")));
    }
}
