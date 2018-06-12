package com.park.model;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
@Component(value="carr")
public class Car implements Vehicle ,ApplicationContextAware,ApplicationEventPublisherAware {
    @Autowired
    private Person driver;
    private ApplicationContext applicationContext;
    private int seats;
    private ApplicationEventPublisher publisher;


    public Car() {
    }

    public Car(Person p1,  int seats) {
        this.driver = p1;
        this.seats =seats;
    }


    public Person getDriver() {
        return driver;
    }


    public void setDriver(Person p1) {
        this.driver = p1;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public void drive() {
        DriveEvent driveEvent = new DriveEvent(this);
        publisher.publishEvent(driveEvent);
        System.out.println("driver is " + driver + " seat is " + seats);

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        ((Person)applicationContext.getBean("driver")).setAge(99);
        System.out.println("set context for Car");
    }

    public  ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @PreDestroy
    public void iDrisr(){
        System.out.println("postConstruct");
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
