package com.fastcampus.functionalprogramming.chapter4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * TODO : Predicate 인터페이스 함수 해석해보기 !
 */
public class Chapter4Section4 {
    public static void main(String[] args) {
        Predicate<Integer> isPositive = x -> x > 0; // 양수인지 테스트
        //System.out.println(isPositive.test(10));

        List<Integer> inputs = Arrays.asList(10, -5, 4, -2, 0,3);
        System.out.println("Positive number : " + filter(inputs, isPositive));
        System.out.println();
        System.out.println("Non-positive number : " + filter(inputs, isPositive.negate()));
        System.out.println();
        System.out.println("Non-negative number : "
                + filter(inputs, isPositive.or((Integer x) -> x == 0 ))); // 0 혹은 0 이상 출력 (0포함)
        System.out.println();
        System.out.println("Positive even numbers : "
                + filter(inputs, isPositive.and(x -> x % 2 == 0))); //양수 중에서 짝수만 출력
    }
    // 이러한 메소드를 helper 라고 부름
    public static <T> List<T> filter(List<T> inputs, Predicate<T> condition ){
        List<T> output = new ArrayList<>();
        for(T input : inputs){
            if(condition.test(input)) // true면 output에 추가
                output.add(input);
        }
        return output;
    }
}
