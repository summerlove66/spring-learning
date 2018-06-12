package com.park.model;

import org.springframework.context.ApplicationEvent;


public class DriveEvent extends ApplicationEvent {


    public DriveEvent(Object source) {
        super(source);
    }

    @Override
    public String toString() {
       return "is called";
    }
}
