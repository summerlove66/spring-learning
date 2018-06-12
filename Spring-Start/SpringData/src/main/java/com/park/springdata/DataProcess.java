package com.park.springdata;

import com.park.dao.DaoImpl;
import com.park.model.Car;
import com.park.model.Person;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;

public class DataProcess {


    public static void main(String[] args) throws SQLException {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-data.xml");
        DaoImpl dao = context.getBean("daoImpl",DaoImpl.class);
        Person person = new Person("monaco1" ,12);
        Car car = new Car();
        car.setDriver(person);
        car.setName("benchi");
        System.out.println(dao.insertCar(car));


    }
}
