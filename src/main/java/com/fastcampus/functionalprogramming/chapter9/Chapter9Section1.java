package com.fastcampus.functionalprogramming.chapter9;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Chapter9Section1 {
    public static void main(String[] args) {
        Supplier<String> supplier = getStringSupplier();
        System.out.println(supplier.get());

        //Curry
        BiFunction<Integer, Integer, Integer> add = (x,y) -> x+y; // 한번에 받는 경우
        // 나눠 받아서 결과 출력
        Function<Integer, Function<Integer,Integer>> curriedAdd
                = x->y-> x+y;

        Function<Integer, Integer> addThree = curriedAdd.apply(3); // 3을 항상 기억함
        int result = addThree.apply(10); // 13
        System.out.println(result);
        System.out.println(addThree.apply(4)); // 7
    }

    //Closure 개념 사용
    public static Supplier<String> getStringSupplier(){
        String hello = "Hello";
        Supplier<String> supplier = () ->{
            String world = "World";
            return hello + world;
        };

        return supplier;
    }
}
