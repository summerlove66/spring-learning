package com.park.proxy;

import com.park.model.Transaction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler{
    private Object target; // 目标类
    private Transaction transaction;

    public DynamicProxy(Object target, Transaction transaction) {
        this.target = target;
        this.transaction = transaction;
    }

    /**
     * @param proxy 目标对象的代理类实例
     * @param method 对应于在代理实例上调用接口方法的Method实例
     * @param args 传入到代理实例上方法参数值的对象数组
     * @return 方法的返回值，没有返回值是null
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        String methodName = method.getName();
        if ("drive".equals(methodName) ) {

            this.transaction.beginTransaction(); // 开启事务
            method.invoke(target,args); // 调用目标方法
            this.transaction.commit(); // 提交事务

        } else {
            method.invoke(target);
        }
        return null;
    }
}
