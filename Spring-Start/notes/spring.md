### 绑定

- 1.继承<parent =“父类的标识"> parent 只能在bean 标签内部

但父类bean中的有collection的属性时。 

 子类bean

	<bean  class="com.park.model.Person" id="p1" parent="superPerson">
        <property name="names">
            <list merge="true">
                <value>beautiful</value>
            </list>
        </property>

        <property  name="age"   value="30" />

    </bean>


则 将 superPerson的names 和 本身names 融合 


- 2.abstruct  位于bean标签内 。标识此bean  不被实例化。 也不能被引用


- 3. autowird
autowired 只能存在bean标签内
A autowire="byName" 时只要匹配B bean 中name 或者id中的一个 即可绑定

A autowire="byType" 时 要求只存在一个符合条件的 B（B对象可以是A属性对象的子类）

以上两种 一旦混淆就报错。


contructor 默认是按照Type来匹配。 在此基础下再 按name(构造方法参数的形参名).
在出现混淆时 ，如果没有指定（待匹配的bean的id或name 的话）按照空的构造方法。

primary=true ， bean能够避免自动装配时的歧义性。 当遇到歧义性的时
候， Spring将会使用首选的bean， 

### spring框架bean管理


- 1.scope

	- prototype 每次都重新创造
	- singleton 只创造一次。 默认为singleton
	
		`<bean id="carr" class="com.park.model.Car" autowire="constructor scope="prototype"/>`
		

- 2.ApplicationContextAware BeanNameAware接口

	- 分别可以拿到 ApplicationContext 对象。 和此次实例化的标识
			
			
			System.out.println(c1.getApplicationContext().getBean("vehicle"));
			
			
			  public void setBeanName(String args) {
			        System.out.println("准备实例化的bean " + args);  #准备实例化的bean p1 ,p1为bean的标识/
			
			   }
			
		
- 3.交给spring管理的类继承 InitializingBean接口(需要重写  afterPropertiesSet()方法 )
			DisposableBean接口 （需要重写destroy() 方法）

	 @Override
	    public void afterPropertiesSet() throws Exception {
	        /**
	         * 初始化结束时调用
	         */
	        System.out.println("----------------");
	    }
	
	    @Override
	    public void destroy() throws Exception {
	        /**
	         * 实例销毁时调用 需要ApplicationContext对象 调用registerShutdownHook()
	         */
	        System.out.println("destory it ");
	    }
	
	 可以不用继承在<bean class="xxxx" init-method="myinit" destroy-method="mydestroy">
	 自定义方法 初始化 以及destory方法一样达到效果


- 4 spring 从工厂类中取得Bean.
		
		 <bean id="bmwCar" class="com.home.factoryMethod.CarStaticFactory" factory-method="getCar">
		        <constructor-arg value="3"></constructor-arg>           
		    </bean>


		 public class CarStaticFactory {
		    private static Map<Integer, Car> map = new HashMap<Integer,Car>();
		
		    static{
		        map.put(1, new Car(1,"Honda",300000));
		        map.put(2, new Car(2,"Audi",440000));
		        map.put(3, new Car(3,"BMW",540000));
		    }
		
		    public static Car getCar(int id){
		        return map.get(id);
		    }
		}

	直接得到3 所对应的 new Car(3,"BMW",5400000)  对象


- 5 需要把实现的类 配置到xml文件里。


	BeanPostProcess 接口（ 有两个默认方法 postProcessBeforeInitialization和postProcessAfterInitialization）
	可以Override 改写。
	postProcessBeforeInitialization 取得的对象 完成了bean的装配.没有完成init-method方法。
	
	具体 http://uule.iteye.com/blog/2094549
	
	BeanFactoryPostProcess 接口（ 需实现 void postProcessBeanFactory( ConfigurableListableBeanFactory beanFactory) 方法)
	
	beanFactory.getBeanDefinitionNames()) 可以得到需要初始化bean的数组。
	beanFactory.getBeanDefinition(beanName) 得到 BeanDefinition对象 ，由此 对象可以获取bean的类名  scope等信息。
	
	
- 6.加载property文件
	 <bean class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
	        <property name="locations" value="data.properties" />
	
	</bean>
	
	一行搞定


	引用property的值时  无需指定文件名 直接引用  <property  name="age"   value="${person.age}" />

- 7 注解 @Required
	spring配置文件加入( <bean class="org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor" />或  <context:annotation-config />)
	 注解必须标注在 set方法上 且必须有形参。可以不是属性。)
	 setXXX(int xxx)  xxx  可以不为属性。
	 表示在spring  所有bean的初始化阶段形参必须传入值。否则抛出异常。
	
	 注解 Autowired （**byType ->@qualifier->primary ->byName)**
	
	 https://blog.csdn.net/zhengyang7754/article/details/62216024
	 xml的配置 会覆盖 注解配置的。(实际上只注入一次)

- 8  JSR-250 Annotations
 	@Resource(name="xxxx") 不指定name时 默认为属性的变量名。
 	当多个bean时，直接选择相应的name来装配 ，

	@PostConstruct与@PreDestroy
	该bean的初始化 和毁灭
	
	**xml constructor注解装配 ->constructorxml配置装配 ->property注解->property xml装配
	Aware接口->ApplicationListener事件驱动->BeanPostProcess->@PostConstruct -> InitializingBean -> init-method ->@PreDestroy ->DisposableBean ->destory-method**

- 8 
	`<context:annotation-config>` is used to activate annotations in beans already registered in the application context (no matter if they were defined with XML or by package scanning).
	
	`<context:component-scan>` can also do what <context:annotation-config> does but `<context:component-scan>` also scans packages to find and register beans within the application context.

@Component(value="xx") 是将类以bean的方式注入spring 容器 value的值 相当于 id. 默认为（类名的全小写）
@Service @Repository @Controller是特殊的Component

- 9 `ReloadableResourceBundleMessageSource `
在java中代码 引入 properties 文件内的数据，

- 10.spring 事件。

实现 `ApplicationEventPublisherAware`接口。 实现 setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) 方法。注入实现触发者对象
ApplicationEventPublisher  触发者   可以改写toString()方法。

ApplicationEvent的子类 事件对象。    如publisher.publishEvent(driveEvent); 触发者.触发事件对象、

 ApplicationListener接口的实现类。  监控对象  .实现void onApplicationEvent(ApplicationEvent applicationEvent) 定义自己的 触发后的操作逻辑。。 监听者接受事件对象。可以根据对象的toString 值 做不同操作。

