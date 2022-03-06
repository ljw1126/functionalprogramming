package com.fastcampus.functionalprogramming.chapter8;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 8.5 Collectors
 */
public class Chapter8Section5 {
    public static void main(String[] args) {
        List<Integer> numbers = Stream.of(3,5,-3,3,4,5)
                                       .collect(Collectors.toList());
        System.out.println(numbers);

        Set<Integer> numberSet = Stream.of(3,5,-3,3,4,5)
                                        .collect(Collectors.toSet());
        System.out.println(numberSet);

        List<Integer> numberList2 = Stream.of(3,5,-3,3,4,5)
               .collect(Collectors.mapping( x -> Math.abs(x),Collectors.toList()));
        System.out.println(numberList2); // 절대값으로 바꾸고 리스트로 변환

        Set<Integer> numberSet2 = Stream.of(3,-5,-3,3,4,5)
                .collect(Collectors.mapping( x -> Math.abs(x),Collectors.toSet()));
        System.out.println(numberSet2);

        int sum = Stream.of(3,5,-3,3,4,5)
                .collect(Collectors.reducing(0,(x,y) -> x + y));
        System.out.println(sum);
    }
}
