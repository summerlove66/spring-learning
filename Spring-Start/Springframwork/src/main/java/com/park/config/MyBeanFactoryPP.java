package com.park.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryPP implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        for(String beanName : beanFactory.getBeanDefinitionNames()){
            System.out.println(beanName +"-------" + beanFactory.getBeanDefinition(beanName).getBeanClassName());
        }
    }
}
