package com.fastcampus.functionalprogramming.chapter8;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * 8.3 Find First / Find Any
 * filter 와 연계해서 많이 사용한다 함
 */
public class Chapter8Section3 {
    public static void main(String[] args) {
        // 음수 아무거나 먼저 찾는거
        Optional<Integer> anyNegativeInter = Stream.of(3,2,-5,6)
                                                   .filter(x-> x < 0)
                                                   .findAny();
        System.out.println(anyNegativeInter.get());

        // 첫번째 양수를 찾기
        Optional<Integer> firstPositiveInteger = Stream.of(3,2,-5,6)
                                                       .filter(x-> x > 0)
                                                       .findFirst();
        System.out.println(firstPositiveInteger.get());

    }
}
