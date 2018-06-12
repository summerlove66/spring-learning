package com.park.config;

import com.park.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyCarBean {


    @Bean("ps")
    public Person getPerson(){
        Person p = new Person();
        p.setAge(10);
        p.setName("pp");
        return p;
    }


}
