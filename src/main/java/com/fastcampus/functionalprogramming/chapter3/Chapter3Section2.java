package com.fastcampus.functionalprogramming.chapter3;

import java.util.function.Function;

import com.fastcampus.functionalprogramming.chapter3.util.Adder;
/**
 * Lambda Expression
 * 이름이 없는 함수(Anonymous function)
 * @author zral1
 */
public class Chapter3Section2 {
 
	public static void main(String[] args) {
		Function<Integer, Integer> myAddr = x -> x + 10; // 입력 타입 유추 가능하고, 매개변수 한개고 리턴만 있으면
		
		int result = myAddr.apply(3);
		System.out.println(result);
	}
	
}
