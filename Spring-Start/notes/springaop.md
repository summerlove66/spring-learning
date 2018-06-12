###@Aspetc 注解 切面类

1.触发对象必须在spring 容器中(Configure类中  @Bean 方法也同样会触发)
执行时期  @Befor @After 

@AfterReturning(并且可以根据返回类型来触发 ，捕获返回值 ，并可以更改第三方调用的返回值）


@AfterThrowing（根据异常类型触发，获得异常本身对象，但不能阻止异常的抛出)  相当于finally 。

@Around( (ProceedingJoinPoint对象 得到 ，触发的方法签名 类名，参数名，可以重置原来的对象某些返回值，但不能改写对象本身，对象本身是否spring容器控制)

2.
执行条件。
  对类 within(xxxx类)

  对参数（args(参数类型))
  	    @Before("args(name)")   //获得实参 ，当参数为String类型的 才会调用此方法。
    	private void loggingAdvice(String name){
        	System.out.println(name + " --------> call");
    	}

  对方法
   - 1. 返回 类型方法名（参数类型）
   - 2. 所在的类 返回 类型方法名（参数类型）
   - 3.public 所在的类 返回 类型方法名（参数类型）
  通配符
  (..)可以为空 ，也可以为其他
  类名X+* :匹配X及其子类所有方法。没有+号的情况下子类 只能匹配（和父类共有的）方法

-------------
关于基本类型。
基本类型在XML中配置 ，class必须为包装类型

可以被 注解或xml装配（Autowired，构造或property都OK)
且在pojo类中 基本类型可以不为包装类型

但是如果存在Aspect的话，必须添加`cglib`包，否则`get*`会报错。

--------------

3.PointCut (讲执行条件 拥方法名来代理)

@PointCut(执行条件)
public void function (){};

可以用 && ||来连接
public class LoggingAspect {

    @Before("allGet()|| allSet()")
    private void loggingAdvice(){
        System.out.println("logging advance  is called");
    }
    @Pointcut("execution(* set*(..))")
    private void allSet(){};

    @Pointcut("execution(*  get*(..))")
    private void allGet(){};

}


4.总结  获得 触发的Aspect的情况 通过两种 参数的话 通过 args和 JoinPoint
在Around 注解的情况下 proceedingJoinPoint.
    
    @Around("@annotation(com.park.aspect.LoggingProcess)")
    public void loggingProcess(ProceedingJoinPoint proceedingJoinPoint){
    System.out.println("开始执行---");
    try {
    proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    } catch (Throwable throwable) {
    throwable.printStackTrace();
    }
    System.out.println(proceedingJoinPoint.getTarget());



