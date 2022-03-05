package com.fastcampus.functionalprogramming.chapter3;

import com.fastcampus.functionalprogramming.chapter3.util.TriFunctional;
public class Chapter3Section4 {

	public static void main(String[] args) {
		// 3개 입력 받는게 없어서 직접 구현해 보는 예제
		TriFunctional<Integer,Integer,Integer,Integer> addThreeNumbers = (x,y,z) -> x + y + z;
		int result = addThreeNumbers.apply(3, 2, 5);
		System.out.println(result);
		
	}
}
