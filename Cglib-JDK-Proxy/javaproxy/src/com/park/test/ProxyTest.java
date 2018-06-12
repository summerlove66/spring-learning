package com.park.test;

import com.park.model.Car;
import com.park.model.Transaction;
import com.park.model.Vehicle;
import com.park.proxy.CglibProxy;
import com.park.proxy.DynamicProxy;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: round1_proxy
 * @description:
 * @author: Hui
 * @create: 2018-06-10 01:41
 **/
public class ProxyTest {

    @Test
    public void testDynamicProxy() throws Throwable {
        System.out.println("JDK dynamic proxy test");
        Transaction transaction = new Transaction();
        Vehicle car = new Car();
        Vehicle proxy = (Vehicle) Proxy.newProxyInstance(Car.class.getClassLoader(), Car.class.getInterfaces(),
                new DynamicProxy(car, transaction));
        System.out.println(proxy.drive());


    }
    @Test
    public void testCglibProxy(){
        System.out.println("cglib proxy test");
        Car car = new Car();
        Transaction transaction = new Transaction();
        CglibProxy cglibProxy = new CglibProxy(car,transaction);
        Car carProxy = (Car)cglibProxy.createProxy();
        System.out.println(carProxy.drive());

    }
}
