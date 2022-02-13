package com.fastcampus.functionalprogramming.chapter6;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter6Section1 {
    public static void main(String[] args) {
        Stream<String> nameStream = Stream.of("Alice","Bob","Charlie");
        List<String> names = nameStream.collect(Collectors.toList()); //stream -> List
        System.out.println(names);

        String[] cityArray = new String[]{"San Jose","Seoul","Tokyo"};
        Stream<String> cityStream = Arrays.stream(cityArray); // Array -> Stream
        List<String> cityList = cityStream.collect(Collectors.toList());
        System.out.println(cityArray);

        Set<Integer> numberSet = new HashSet<>(Arrays.asList(3,5,7));
        Stream<Integer> numberStream = numberSet.stream();  // Collections type -> stream
        List<Integer> numberList = numberStream.collect(Collectors.toList());
        System.out.println(numberList);
    }

}
