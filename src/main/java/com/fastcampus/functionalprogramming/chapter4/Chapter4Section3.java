package com.fastcampus.functionalprogramming.chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * Consumer하고 차이라면 매개변수(input)이 2개라는 점
 */
public class Chapter4Section3 {
    public static void main(String[] args) {
        BiConsumer<Integer, Double> myDoubleProcessor =
                (index, input)->
                    System.out.println("Processing " + input + " at index " + index);

        List<Double> inputs = Arrays.asList(1.1, 2.2, 3.3, 4.4);
        process(inputs, myDoubleProcessor);
    }

    public static <T> void process(List<T> inputs, BiConsumer<Integer, T> processor){
        for(int i=0; i < inputs.size(); i++)
            processor.accept(i, inputs.get(i));
    }
}
