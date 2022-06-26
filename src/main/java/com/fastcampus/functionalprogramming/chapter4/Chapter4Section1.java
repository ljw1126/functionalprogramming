package com.fastcampus.functionalprogramming.chapter4;

import java.util.function.Supplier;

public class Chapter4Section1 {
    public static void main(String[] args) {
        Supplier<String> myStringSupplier = () ->  "hello world";
        System.out.println(myStringSupplier.get());

        Supplier<Double> myRandomDoubleSuppiler = () -> Math.random();
        printRandomDoubles(myRandomDoubleSuppiler,5);
    }

    // helper method
    public static void printRandomDoubles(Supplier<Double> randomSupplier, int count){
        for(int i=0 ; i< count ; i++)
            System.out.println(randomSupplier.get());
    }
}
