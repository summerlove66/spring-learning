### Spring 代理

---
- **半自动代理（主要通过bean工厂）**
    - org.springframework.aop.framework.ProxyFactoryBean
    - org.springframework.transaction.interceptor.TransactionProxyFactoryBean
    - 代理对象 通过实现 MethodInterceptor ,MethodBeforeAdvice 接口来增强
 - **全自动代理（Aop)**
    - advisor 主要用于事务
    - Aspect 
    
