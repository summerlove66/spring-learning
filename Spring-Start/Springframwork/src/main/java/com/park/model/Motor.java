package com.park.model;

public class Motor implements Vehicle {
    private String driver;


    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        System.out.println("准备set " +driver);
        this.driver = driver;

    }

    @Override
    public String toString() {
        return "Motor{" +
                "driver='" + driver + '\'' +
                '}';
    }

    @Override
    public void drive() {
        System.out.println(driver + " driver his motor ");
    }
}
