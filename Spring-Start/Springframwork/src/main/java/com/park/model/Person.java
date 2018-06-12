package com.park.model;

import java.util.List;

public class Person {
    private List<String> names;
    private int age;


    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        System.out.println("set person names" +names);
        this.names = names;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + names + '\'' +
                ", age=" + age +
                '}';
    }



}
