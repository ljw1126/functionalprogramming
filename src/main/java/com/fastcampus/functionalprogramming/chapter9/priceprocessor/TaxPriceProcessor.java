package com.fastcampus.functionalprogramming.chapter9.priceprocessor;

import com.fastcampus.functionalprogramming.chapter9.model.Order;

import java.math.BigDecimal;
import java.util.function.Function;

public class TaxPriceProcessor implements Function<Order,Order>{

    private final BigDecimal taxRate;

    public TaxPriceProcessor(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
    //세율 적용  9.8%일때  /100하고 +1 일을하면 .. 1.098
    @Override
    public Order apply(Order order) {
        return order.setAmount(order.getAmount()
            .multiply(taxRate.divide(new BigDecimal(100)).add(BigDecimal.ONE))
        );
    }
}
