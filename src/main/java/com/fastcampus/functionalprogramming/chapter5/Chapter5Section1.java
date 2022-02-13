package com.fastcampus.functionalprogramming.chapter5;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.BiFunction;
/**
 * 1. 클래스의 static method(정적 메서드)를 지정할때
 * 2. 이미 선언되어 있는 객체(object)의 instance method를 지정할 때
 * TODO :
 */
public class Chapter5Section1 {

    public static int calculate(int x, int y, BiFunction<Integer, Integer, Integer> operator){
        return operator.apply(x,y);
    }

    public static int multiply(int x, int y){
        return x * y;
    }

    public int subtract(int x, int y){
        return x - y;
    }

    public void myMethod(){
        System.out.println(calculate(10,3,this::subtract));
    }

    public static void main(String[] args) {
        //int a = Integer.parseInt("15");
        Function<String, Integer> str2int = Integer::parseInt;
        System.out.println(str2int.apply("15"));

        String str = "hello";
        Predicate<String> equalsToHello = str::equals;
        System.out.println(equalsToHello.test(str));

        System.out.println();
        System.out.println(calculate(8,2, (x,y) -> x+y)); // TODO. 이해하기

        //static 메서드이고 클래스 내부 메서드 이기때문에
        System.out.println(calculate(8,2, Chapter5Section1::multiply));

        // instance 메서드 넘겨줄 수 있음
        Chapter5Section1 instance = new Chapter5Section1();
        System.out.println(calculate(8,2, instance::subtract));
        instance.myMethod();
    }
}
