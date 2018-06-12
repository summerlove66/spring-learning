package com.park.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PropertyTest {


    @Test
    public void getProperties(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-conf.xml");
        System.out.println(context.getMessage("greeting",new String[]{"kaixin","hhaa","sds"},"talk",null) );
    }
}
