package com.park.Aspect;

import com.park.dao.DaoImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DaoAspect {

    //@Around("execution(* com.park.dao.*.*(..))")
    public Object DaoImplaLogger(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("开始执行数据库操作");
        try {
            Object res = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
            System.out.println("执行成功");
            return res;
        } catch (Exception e){
            System.out.println("执行错误2：" +e.getMessage());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("执行错误1:" +throwable.getMessage());

        }

        return null;
    }



}
