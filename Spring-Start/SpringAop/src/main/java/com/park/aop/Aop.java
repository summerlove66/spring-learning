package com.park.aop;

import com.park.model.Car;
import com.park.model.Person;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Aop{

    public static void main(String[] args) throws Exception {

        AbstractApplicationContext context =  new  ClassPathXmlApplicationContext("spring-aop.xml");
        Car car = context.getBean(Car.class);

        car.drive("shanghai");


    }

}