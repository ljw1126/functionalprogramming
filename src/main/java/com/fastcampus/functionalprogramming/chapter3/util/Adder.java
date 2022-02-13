package com.fastcampus.functionalprogramming.chapter3.util;

import java.util.function.Function;

public class Adder implements Function<Integer, Integer>{

	@Override
	public Integer apply(Integer t) {
		return 10 + t;
	}
}
