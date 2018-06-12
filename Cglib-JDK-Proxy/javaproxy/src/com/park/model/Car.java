package com.park.model;


public class Car implements Vehicle {
    @Override
    public String drive() {
       return  "drive my car ";
    }


    public void display(){
        System.out.println("display  it is great car");
    }
}
