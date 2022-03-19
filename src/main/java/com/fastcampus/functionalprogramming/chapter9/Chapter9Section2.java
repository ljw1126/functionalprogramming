package com.fastcampus.functionalprogramming.chapter9;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter9Section2 {
    public static void main(String[] args) {
        if ( returnTrue() || returnFalse()) {//앞에 값이 있으면 앞에꺼 실행, 없으면 뒤에꺼
            System.out.println("true ==== basic ");
        }

        if (or(returnTrue(), returnFalse())) { // 둘다 계산된 후에 메서드 호출 (최적화 x)
            System.out.println("true ==== or ");
        }

        //최적화를 한다면
        if (lazyOr(() -> returnTrue(), () -> returnFalse())) {//retrunFalse() 실행 x
            System.out.println("lazyOr ==== true");
        }

        // stream 종결 처리시 lazy evaluation을 한다.
        Stream<Integer> integerStream = Stream.of(3,-2,5,8,-3,10)
                .filter(x->x>0)
                .peek(x-> System.out.println("peeking" + x))
                .filter(x -> x%2 == 0);
        System.out.println("Before collect");

        List<Integer> integers = integerStream.collect(Collectors.toList());
        System.out.println("After collect : " + integers);

        /**
           Before collect
           peeking3     // *.collect() 호출되었을대 실행됨
           peeking5
           peeking8
           peeking10
           After collect : [8, 10]
         */
    }

    public static boolean or(boolean x, boolean y) {
        return x || y;
    }

    public static boolean lazyOr(Supplier<Boolean> x, Supplier<Boolean> y) {
        return x.get() || y.get();   // x.get() 이 true 이면 뒤에꺼는 호출 x
    }

    public static boolean returnTrue() {
        System.out.println("Returning true");
        return true;
    }

    public static boolean returnFalse() {
        System.out.println("Returning false");
        return false;
    }
}
