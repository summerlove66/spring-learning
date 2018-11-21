package com.park.spring;

import com.park.model.Car;
import com.park.model.Vehicle;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Driving implements Vehicle {
    private Vehicle vehicle;



    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;

    }

    public static void main(String[] args) {
      //  BeanFactory factory = new ClassPathXmlApplicationContext("spring-conf.xml");
        AbstractApplicationContext factory = new ClassPathXmlApplicationContext("spring-conf.xml");



        factory.registerShutdownHook();
        Car c1 = (Car) factory.getBean("carr");
        c1.drive();
//

    }


    @Override
    public void drive() {
        vehicle.drive();
    }


    @Override
    public String toString() {
        return "Driving{" +
                "vehicle=" + vehicle +
                '}';
    }
}
