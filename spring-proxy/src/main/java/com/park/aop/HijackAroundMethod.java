package com.park.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;
import java.util.Arrays;

public class HijackAroundMethod implements MethodInterceptor ,MethodBeforeAdvice {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        System.out.println("Method name : "
                + methodInvocation.getMethod().getName());
        System.out.println("Method arguments : "
                + Arrays.toString(methodInvocation.getArguments()));

        System.out.println("HijackAroundMethod : Before method hijacked!");

        try {
            Object result = methodInvocation.proceed();
            System.out.println("HijackAroundMethod : Before after hijacked!");

            return result;

        } catch (IllegalArgumentException e) {

            System.out.println("HijackAroundMethod : Throw exception hijacked!");
            throw e;
        }
    }

    @Override
    public void before(Method method, Object[] objects, @Nullable Object o) throws Throwable {
        System.out.println("高考顺利");
    }
}
