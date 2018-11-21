package com.park.model;

import com.park.aspect.LoggingProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {


    private Person driver;


    public Car() {
    }


    public Person getDriver() throws Exception {

      return driver;
    }
    @Autowired
    public void setDriver(Person driver) {
        this.driver = driver;

    }


    @Override
    public String toString() {
        return "Car{" +
                ", driver=" + driver +
                '}';
    }
    @LoggingProcess
    public void drive(String address) {
        System.out.println(driver.getName()+ "will drive to " +address);
    }


}
