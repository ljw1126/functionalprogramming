package com.fastcampus.functionalprogramming.chapter5.model;

public class Suv extends Car{
    public Suv(String name, String brand) {
        super(name, brand);
    }

    public void drive(){
        System.out.println("Driving a Suv : " + name + " from " + brand);
    }
}
