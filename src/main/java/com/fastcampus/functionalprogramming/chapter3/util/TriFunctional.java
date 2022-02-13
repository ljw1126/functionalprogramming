package com.fastcampus.functionalprogramming.chapter3.util;

//단 하나의 추상 메소드만 가짐 (functionalInterface)
@FunctionalInterface
public interface TriFunctional<T,U,V,R> {
	R apply(T t, U u, V v);
}
