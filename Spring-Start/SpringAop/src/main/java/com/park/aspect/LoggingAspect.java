package com.park.aspect;

import com.park.model.Person;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {


   @Around("@annotation(com.park.aspect.LoggingProcess)")
    public void loggingProcess(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("开始执行---");
        try {
            proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println(proceedingJoinPoint.getTarget());
        System.out.println(proceedingJoinPoint.getThis());
        System.out.println(proceedingJoinPoint.getSignature());
       System.out.println(proceedingJoinPoint.getArgs()[0]);
        System.out.println("完成");
    }


    @Pointcut("execution(* get*(..))")
    private void allGet(){}

    @Pointcut("execution(* set*(..))")
    private void allSet(){}
}
