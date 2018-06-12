package com.park.config;

import com.park.model.Car;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;


public class DisplayBeans implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("carr")){
            System.out.println(((Car)bean).getDriver() +"bean post  ----");
        }else {
            System.out.println("before" +beanName);
        }

        return bean;

    }
}
