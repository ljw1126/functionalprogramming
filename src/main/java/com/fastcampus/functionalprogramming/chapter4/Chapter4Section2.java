package com.fastcampus.functionalprogramming.chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Consumer의 accept는 파라미터만 받고 리턴 없음(void)
 * 입력 받아 정의해둔 내부 로직 처리 하는 함수
 */
public class Chapter4Section2 {
    public static void main(String[] args) {
        Consumer<String> myStringConsumer = (String str) -> System.out.println(str);
        myStringConsumer.accept("hello world");
        myStringConsumer.accept("tester");

        List<Integer> integerInputs = Arrays.asList(4,2,3,1); // immutable , 이후 값 추가 불가  java.lang.UnsupportedOperationException
        Consumer<Integer> myIntegerProcessor =  x ->
                System.out.println("Procession integer" + x);
        process(integerInputs, myIntegerProcessor);

        Consumer<Integer> myDifferentIntegerProcessor = x ->
                System.out.println("Processing integer in different way :" + x);
        process(integerInputs, myDifferentIntegerProcessor);

        Consumer<Double> myDoubleProcessor = x->
                System.out.println("Processing double : " + x);
        List<Double> doubleInputs = Arrays.asList(1.1,2.2,3.3);
        genericProcess(doubleInputs, myDoubleProcessor);

    }

    public static void process(List<Integer> inputs, Consumer<Integer> processor){
        for(Integer input : inputs){
            processor.accept(input);
        }
    }

    // generic <T> 타입을 input으로 받겠다.
    public static <T> void genericProcess(List<T> inputs, Consumer<T> processor){
        for(T input : inputs){
            processor.accept(input);
        }
    }
}
