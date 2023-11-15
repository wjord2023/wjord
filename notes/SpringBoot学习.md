# **SSM框架**

# ***Spring6***



## Spring特点

#### 非侵入式

不需要依赖其他组件

#### **IoC**(Inverse of Control)

控制反转，反转资源获取方向

#### **AOP**(Aspect Oriented Programing)

面向切面编程，在不改变源代码的基础上增强代码功能

#### 容器

Spring IoC是一个容器，提高开发效率

#### 组件化

使用xml和java注解组合对象

#### 一站式

在IoC和AOP的基础上可以整合其他框架



## 入门

1. 创建maven聚合工程

   创建父工程再创建子模块

2. 引入spring相关依赖

   ```xml
   <dependencies>
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-context</artifactId>
               <version>6.0.11</version>
           </dependency>
           <dependency>
               <groupId>org.junit.jupiter</groupId>
               <artifactId>junit-jupiter-api</artifactId>
               <version>5.6.3</version>
           </dependency>
       </dependencies>
   ```

   

3. 创建类，定义属性和方法

   ```java
   package com.wjord;
   
   public class User {
       public void add() {
           System.out.println("add...");
       }
   }
   ```

   

4. 按照spring要求创建配置文件（xml格式）

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
       <!--完成user对象创建
           bean标签的id属性：用于指定bean的唯一标识
           class属性：要创建对象所在类的全路径（包名称+类名称）
       -->
       <bean id="user" class="com.wjord.User"/>
   </beans>
   ```

   

5. 在spring配置文件配置相关信息

6. 进行最终测试

   ```java
   package com.wjord;
   
   import org.junit.jupiter.api.Test;
   import org.springframework.context.ApplicationContext;
   import org.springframework.context.support.ClassPathXmlApplicationContext;
   
   public class TestUser {
   
       @Test
       public void testUserObject() {
           // 加载spring配置文件
           ApplicationContext context =
                   new ClassPathXmlApplicationContext("bean.xml");
           // 获取创建的对象
           User user = (User) context.getBean("user");
           System.out.println(user);
           // 使用对象调用方法进行测试
           user.add();
       }
   }
   
   ```

   1.之前创建对象，无参数构造执行？（会执行）
   
   2.不用new方式，还可以怎么创建对象？
   
   （反射）：
   
   1.加载bean.xml配置文件
   
   2.对xml文件进行解析操作
   
   3.获取xml文件bean标签属性值（id属性值和class属性值）
   
   4.使用反射根据类全路径创建对象
   
   3.创建的对象放到哪里？
   
   （Map<String,BeanDefinition>beanDefinitionMap）
   
   （Key:唯一标识；value:类的定义（描述信息））
   
   
   
   ## 启用Log4j2日志框架
   
   #### 日志信息的级别：TRACE<DEBUG<INFO<WARN<ERROR<FATAL
   
   TRANCE:追踪，最低，相当于追踪程序的执行
   
   DEBUG:调试，一般在开发中，都将其设置为最低的日志级别
   
   INFO:信息，输出重要的信息，使用较多
   
   WARN:警告
   
   ERROR:错误
   
   FATAL:严重错误
   
   级别高的会自动屏蔽级别低的
   
   #### 引入Log4j2依赖
   
   ```xml
   <!--log4j2-->
           <dependency>
               <groupId>org.apache.logging.log4j</groupId>
               <artifactId>log4j-core</artifactId>
               <version>2.19.0</version>
           </dependency>
           <dependency>
               <groupId>org.apache.logging.log4j</groupId>
               <artifactId>log4j-slf4j2-impl</artifactId>
               <version>2.19.0</version>
           </dependency>
   ```
   
   配置log4j2.xml
   
   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <!--Configuration后面的status，这个用于设置log4j2自身内部的信息输出，可以不设置，当设置成trace时，你会看到log4j2内部各种详细输出-->
   <!--monitorInterval：Log4j能够自动检测修改配置 文件和重新配置本身，设置间隔秒数(最小是5秒钟)-->
   <configuration monitorInterval="5" status="warn">
       <!--日志级别以及优先级排序: OFF > FATAL > ERROR > WARN > INFO > DEBUG > TRACE > ALL -->
   
   
       <!--变量配置-->
       <Properties>
           <!-- 格式化输出：%date表示日期(可缩写成%d，后同)，%thread表示线程名，%-5level：级别从左显示5个字符宽度 %msg：日志消息，%n是换行符-->
           <!-- %logger{36} 表示 Logger 名字最长36个字符 -->
           <property name="LOG_PATTERN" value="%d{yyyy-MM-dd HH:mm:ss,SSS} %highlight{%-5level} [%t] %highlight{%c{1.}.%M(%L)}: %msg%n" />
           <!-- 定义日志存储的路径 -->
           <property name="FILE_PATH" value="log" />
           <!--<property name="FILE_NAME" value="myProject" />-->
       </Properties>
   
       <!--此节点有三种常见的子节点：Console,RollingFile,File-->
       <appenders>
   
           <!--console节点用来定义输出到控制台的Appender-->
           <!--target:SYSTEM_OUT或SYSTEM_ERR,一般只设置默认:SYSTEM_OUT-->
           <console name="Console" target="SYSTEM_OUT">
               <!--输出日志的格式,默认为：%m%n,即只输出日志和换行-->
               <PatternLayout pattern="${LOG_PATTERN}"/>
               <!--阈值过滤器，控制台只输出level及其以上级别的信息（onMatch），其他的直接拒绝（onMismatch）-->
               <ThresholdFilter level="info" onMatch="ACCEPT" onMismatch="DENY"/>
           </console>
   
           <!--        &lt;!&ndash;文件会打印出所有信息，这个log每次运行程序会自动清空，由append属性决定，适合临时测试用&ndash;&gt;-->
           <!--        <File name="Filelog" fileName="${FILE_PATH}/test.log" append="false">-->
           <!--            <PatternLayout pattern="${LOG_PATTERN}"/>-->
           <!--        </File>-->
   
   
           <!-- 这个会打印出所有的debug及以下级别的信息，每次大小超过size，则这size大小的日志会自动存入按年份-月份建立的文件夹下面并进行压缩，作为存档-->
           <RollingFile name="RollingFileDebug" fileName="${FILE_PATH}/debug.log" filePattern="${FILE_PATH}/debug/DEBUG-%d{yyyy-MM-dd}_%i.log.gz">
               <!--阈值过滤器，控制台只输出level及其以上级别的信息（onMatch），其他的直接拒绝（onMismatch）-->
               <ThresholdFilter level="debug" onMatch="ACCEPT" onMismatch="DENY"/>
               <!--如果配置的是“%d{yyyy-MM}”，滚动时间单位就是月。“%d{yyyy-MM-dd}”，滚动时间单位就是天-->
               <PatternLayout pattern="${LOG_PATTERN}"/>
               <!--指定滚动日志的策略，就是指定新建日志文件的时机-->
               <Policies>
                   <!--interval属性用来指定多久滚动一次，时间单位取决于<PatternLayout pattern>，modulate属性调整时间，true：0点为基准滚动，false：服务器启动时间开始滚动-->
                   <TimeBasedTriggeringPolicy interval="1" modulate="true" />
                   <SizeBasedTriggeringPolicy size="100MB"/>
               </Policies>
               <!-- DefaultRolloverStrategy属性如不设置，则默认为最多同一文件夹下7个文件开始覆盖-->
               <DefaultRolloverStrategy max="15">
                   <!--删除15天之前的日志-->
                   <Delete basePath="${FILE_PATH}" maxDepth="2">
                       <IfFileName glob="*/*.log.gz" />
                       <IfLastModified age="360H" />
                   </Delete>
               </DefaultRolloverStrategy>
           </RollingFile>
   
   
           <!-- 这个会打印出所有的warn及以下级别的信息，每次大小超过size，则这size大小的日志会自动存入按年份-月份建立的文件夹下面并进行压缩，作为存档-->
           <RollingFile name="RollingFileInfo" fileName="${FILE_PATH}/info.log" filePattern="${FILE_PATH}/info/INFO-%d{yyyy-MM-dd}_%i.log.gz">
               <!--阈值过滤器，控制台只输出level及其以上级别的信息（onMatch），其他的直接拒绝（onMismatch）-->
               <ThresholdFilter level="info" onMatch="ACCEPT" onMismatch="DENY"/>
               <PatternLayout pattern="${LOG_PATTERN}"/>
               <Policies>
                   <!--interval属性用来指定多久滚动一次，时间单位取决于<PatternLayout pattern>，modulate属性调整时间，true：0点为基准滚动，false：服务器启动时间开始滚动-->
                   <TimeBasedTriggeringPolicy interval="1" modulate="true" />
                   <SizeBasedTriggeringPolicy size="100MB"/>
               </Policies>
               <!-- DefaultRolloverStrategy属性如不设置，则默认为最多同一文件夹下7个文件开始覆盖-->
               <DefaultRolloverStrategy max="15"/>
           </RollingFile>
   
   
           <!-- 这个会打印出所有的error及以下级别的信息，每次大小超过size，则这size大小的日志会自动存入按年份-月份建立的文件夹下面并进行压缩，作为存档-->
           <RollingFile name="RollingFileError" fileName="${FILE_PATH}/error.log" filePattern="${FILE_PATH}/error/ERROR-%d{yyyy-MM-dd}_%i.log.gz">
               <!--阈值过滤器，控制台只输出level及其以上级别的信息（onMatch），其他的直接拒绝（onMismatch）-->
               <ThresholdFilter level="error" onMatch="ACCEPT" onMismatch="DENY"/>
               <PatternLayout pattern="${LOG_PATTERN}"/>
               <Policies>
                   <!--interval属性用来指定多久滚动一次，时间单位取决于<PatternLayout pattern>，modulate属性调整时间，true：0点为基准滚动，false：服务器启动时间开始滚动-->
                   <TimeBasedTriggeringPolicy interval="1" modulate="true" />
                   <SizeBasedTriggeringPolicy size="100MB"/>
               </Policies>
               <!-- DefaultRolloverStrategy属性如不设置，则默认为最多同一文件夹下7个文件开始覆盖-->
               <DefaultRolloverStrategy max="15"/>
           </RollingFile>
           <!--启用异步日志，阻塞队列最大容量为20000，超出队列容量时是否等待日志输出，不等待将直接将日志丢弃-->
           <Async name="Async" bufferSize="20000" blocking="true">
               <AppenderRef ref="Console"/>
               <AppenderRef ref="RollingFileDebug"/>
               <AppenderRef ref="RollingFileInfo"/>
               <AppenderRef ref="RollingFileError"/>
           </Async>
       </appenders>
   
   
       <!--Logger节点用来单独指定日志的形式，比如要为指定包下的class指定不同的日志级别等。-->
       <!--然后定义loggers，只有定义了logger并引入的appender，appender才会生效-->
       <loggers>
           <!--过滤掉spring和mybatis的一些无用的DEBUG信息-->
           <logger name="org.mybatis" level="info" additivity="false">
               <AppenderRef ref="Async"/>
           </logger>
           <!--监控系统信息-->
           <!--若是additivity设为false，则 子Logger 只会在自己的appender里输出，而不会在 父Logger 的appender里输出。-->
           <Logger name="org.springframework" level="info" additivity="false">
               <AppenderRef ref="Async"/>
           </Logger>
           <!--root 节点用来指定项目的根日志，level:日志输出级别，共有8个级别，按照从低到高为：All < Trace < Debug < Info < Warn < Error < Fatal < OFF.-->
           <root level="debug">
               <AppenderRef ref="Async" />
           </root>
       </loggers>
   
   
   </configuration>
   
   ```
   
   
   
   ## IoC容器
   
   #### Inversion of Control 控制反转
   
   Spring通过**IoC容器**来管理**所有Java对象的实例化和初始化，控制对象与对象之间的依赖关系**。
   
   我们将由IoC容器管理的Java对象称为Spring Bean,它与使用关键字new创建的java对象没有任何区别
   
   
   
   #### 过程
   
   容器放bean对象，使用map集合（key,value）放取方便
   
   xml配置文件（Bean定义信息（BeanDefinition））
   
   接口（BeanDefinitionReader）加载配置文件
   
   存储到IoC容器（Bean定义信息）->进行实例化(BeanFactory工厂+反射)->初始化->最终对象（context.getBean("")获取）
   
   
   
   ![image-20231114223112383](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231114223112383.png)

## 依赖注入

### DI（Dependency Injection）

依赖注入实现了控制反转的思想 

指Spring创建对象的过程中，将对象依赖属性通过配置进行注入

Bean管理说的是：Bean对象的创建，以及Bean对象中属性的赋值（或者叫Bean对象之间关系的维护）

![image-20231114223954386](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231114223954386.png)

## 基于xml管理bean

获取Bean

```java
@Test
    public static void main(String[] args) {
        ApplicationContext context = new 	ClassPathXmlApplicationContext("bean.xml");
        // 根据id获取bean对象
        User user = (User) context.getBean("user");
        System.out.println(user);
        // 根据类型获取bean对象
        User user1 = context.getBean(User.class);
        System.out.println(user1);
        // 根据类型+id获取bean对象
        User user2 = context.getBean("user", User.class);
        System.out.println(user2);

    }
```

![image-20231114230723580](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231114230723580.png)

> 先创建一个接口interface UserDao();
>
> 再创建实现类1 UserDaoImpl实现UserDao接口
>
> 如果组件实现了接口，可以根据接口类型获取bean
>
> 如果一个有多个实现类，每个类都配置了bean，不能根据接口类型获取bean因为bean不唯一	

依赖注入：setter注入和构造器注入

> 1、类有属性，创建对象过程中，向属性设置值

> 第一种方式：基于set方法完成
>
> 第一步 创建类，定义属性，生成set方法
>
> 第二步 在spring文件中添加配置

```xml
<!--setter注入-->
    <bean id="book" class="com.wjord.di.Book">
        <property name="name" value="wjord"/>
        <property name="author" value="w"/>
    </bean>
```

>第二种方式：基于构造器完成
>
>第一步 创建类，定义属性,生成构造器
>
>第二步 配置文件

```xml
<!--构造器注入-->
    <bean id="book2" class="com.wjord.di.Book">
        <constructor-arg name="name" value="wjord"/>
        <constructor-arg index="1" value="w"/>
    </bean>
```

特殊类型注入

![image-20231115112222387](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115112222387.png)

![image-20231115112311946](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115112311946.png)

![image-20231115112349328](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115112349328.png)

![image-20231115112504541](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115112504541.png)

> 对象类型属性注入 
>
> 方法一：引用外部bean
>
> ```xml
> <!--
>         1.创建两个对象：dept和emp
>         2.在emp的标签里面，使用property标签为属性赋值
>     -->
>     <bean id="dept" class="com.wjord.ditest.Dept">
>         <property name="name" value="开发部"/>
>     </bean>
> 	
>     <bean id="emp" class="com.wjord.ditest.Emp">
>         <!--注入普通类型属性-->
>         <property name="name" value="wjord"/>
>         <property name="age" value="1"/>
>         <!--注入对象类型属性
>             ref:引用类型，指向bean的id
>             value:基本类型
>         -->
>         <property name="dept" ref="dept"/>
>     </bean>
> ```
>
> 方法二：内部bean
>
> ```xml
> <bean id="emp2" class="com.wjord.ditest.Emp">
>         <property name="name" value="wjord"/>
>         <property name="age" value="1"/>
> 
>         <property name="dept">
>             <bean id="dept2" class="com.wjord.ditest.Dept">
>                 <property name="name" value="开发部"/>
>             </bean>
>         </property>
>     </bean>
> ```
>
> 方法三：级联属性赋值
>
> ```xml
> <!--级联赋值-->
>     <bean id="dept" class="com.wjord.ditest.Dept">
>         <property name="name" value="开发部"/>
>     </bean>
>     <bean id="emp3" class="com.wjord.ditest.Emp">
>         <property name="name" value="wjord"/>
>         <property name="age" value="1"/>
> 
>         <property name="dept" ref="dept"/>
>         <property name="dept.name" value="发部"/>
>     </bean>
> ```

![image-20231115120700800](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115120700800.png)

![image-20231115121331853](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115121331853.png)

![image-20231115122420497](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115122420497.png)

![image-20231115124534059](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115124534059.png)

![image-20231115124652872](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115124652872.png)

### 外部文件引入

> 
>
> 实现过程：
>
> 1.引入数据库相关的依赖
>
> ```xml
> <dependency>
>             <groupId>mysql</groupId>
>             <artifactId>mysql-connector-java</artifactId>
>             <version>8.0.28</version>
>         </dependency>
>         <dependency>
>             <groupId>com.alibaba</groupId>
>             <artifactId>druid</artifactId>
>             <version>1.2.6</version>
>         </dependency>
> ```
>
> 
>
> 2.创建外部属性文件，properties格式，定义数据库信息：用户名 密码 地址等 
>
> ```properties
> jdbc.user=root
> jdbc.password=102302
> jdbc.url=jdbc:mysql://localhost:3306/spring?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC
> jdbc.driver=com.mysql.cj.jdbc.Driver
> ```
>
> ```xml
> <beans xmlns="http://www.springframework.org/schema/beans"
>        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>        xmlns:context="http://www.springframework.org/schema/context"
>        xsi:schemaLocation="http://www.springframework.org/schema/context
>        http://www.springframework.org/schema/context/spring-context.xsd
>        http://www.springframework.org/schema/beans
>        http://www.springframework.org/schema/beans/spring-beans.xsd">
> ```
>
> 引入外部属性文件
>
> ```xml
> <context:property-placeholder location="classpath:jdbc.properties"/>
> ```
>
> 完成数据库信息的注入
>
> (原本做法)
>
> ```java
> public class TestJdbc {
>     @Test
>     public void test1() {
>         DruidDataSource dataSource = new DruidDataSource();
>         dataSource.setUrl("jdbc:mysql://localhost:3306/spring?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC");
>         dataSource.setUsername("root");
>         dataSource.setPassword("102302");
>         dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
>     }
> }
> ```
>
> （spring做法）
>
> ```xml
> <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
>         <property name="driverClassName" value="${jdbc.driver}"/>
>         <property name="url" value="${jdbc.url}"/>
>         <property name="username" value="${jdbc.user}"/>
>         <property name="password" value="${jdbc.password}"/>
>     </bean>
> ```
>
> ```java
>     @Test
>     public void test2() {
>         ApplicationContext context = new
>                 ClassPathXmlApplicationContext("bean-jdbc.xml");
>         DruidDataSource dataSource = context.getBean("dataSource", DruidDataSource.class);
>         System.out.println(dataSource.getUrl());
>     }
> ```
>
> 3.创建spring配置文件，引入context命名空间引入属性文件。使用表达式完成注入

bean的作用域 

![image-20231115131922722](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115131922722.png)

bean的生命周期 

![image-20231115132128770](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115132128770.png)s

![image-20231115133038899](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115133038899.png)

> init-method为初始时会调用的方法，destroy-method为销毁时会调用的方法

### FactoryBean

![image-20231115153216630](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115153216630.png)

## 基于xml自动装配

>1.编写controller
>
>定义service类型属性
>
>生成service属性set方法
>
>```java
>public class UserController {
>    private UserService userService;
>
>    public void setUserService(UserService userService) {
>        this.userService = userService;
>    }
>
>    public void addUserController(){
>        // 调用service层的方法
>        System.out.println("add user controller");
>        userService.addUserService();
>    }
>}
>```
>
>
>
>2.编写service的接口和实现类 
>
>dao类型属性
>
>生成dao类型属性set方法
>
>```java
>public class UserServiceImpl implements UserService{
>    private UserDao userDao;
>
>    public void setUserDao(UserDao userDao) {
>        this.userDao = userDao;
>    }
>
>    @Override
>    public void addUserService() {
>        System.out.println("add user service");
>        userDao.addUserDao();
>    }
>}
>```
>
>
>
>3.编写dao接口和实现类
>
>```java
>package com.wjord.auto.dao;
>
>public class UserDaoImpl implements UserDao{
>    @Override
>    public void addUserDao() {
>        System.out.println("add user dao");
>    }
>}
>
>```



## ***基于注解管理Bean***

> 注解：是代码中的一个特殊标记
>
> 格式：@注解名称（属性1= 属性...）
>
> 类上面，属性上面，方法上面都可以写注解
>
> 用注解开发人员可以在不改变原有代码和逻辑的情况下，在源代码中注入补充信息

第一步 引入相关依赖

第二步 开启组件扫描

```xml
<!--开启注解扫描-->
    <context:component-scan base-package="com.wjord"/>
```

> 排除

![image-20231115161711838](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115161711838.png)

> 只扫描一部分

![image-20231115161752377](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115161752377.png)

3.用注解定义Bean

![image-20231115162009813](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115162009813.png)

```java
@Component(value = "user")//<bean id=""(默认为方法名首字母小写) class="...">
public class User {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        User user = (User) context.getBean("user");
        System.out.println(user);
    }
}
```

### @Autowired注入

##### 1.属性注入

单独使用默认是byTpye

> 1.bean对象创建
>
> 2.定义相关属性，在属性上面添加注释

```java
@Service
public class UserServiceImpl implements UserService{

    // 注入dao

    @Autowired
    private UserDao userDao;
    @Override
    public void add() {
        userDao.add();
        System.out.println("service add...");
    }
}

@Repository
public class UserDaoImpl implements UserDao{
    @Override
    public void add() {
        System.out.println("dao add...");
    }
}

@Controller
public class UserController {

    // 注入service
    @Autowired //根据类型找到对应对象，完成注入
    private UserService userService;

    public void add(){
        userService.add();
        System.out.println("controller add...");
    }
}
```

##### 2.set注入

```java
private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
```

##### 3.构造器注入

```java
private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
```

##### 4.形参上注入 

```java
private UserDao userDao;
    
    public UserServiceImpl(@Autowired UserDao userDao) {
        this.userDao = userDao;
    }

private UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }
```

##### 5.只有构造函数，无注解

```java
private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
```

> 只能写一个有参数构造，不能有无构造

##### 6.@Autowired和@Qualifier注释联合使用

```java
@Autowired
@Qualifier("userRedisDao")
private UserDao userDao; 
```

### @Resource注入

是JDK扩展包里的，更有通用性

![image-20231115165804140](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115165804140.png)

![image-20231115165817704](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115165817704.png)

```java
@Service("myuserService")
public class UserServiceImpl implements UserService {


    @Resource(name = "myuserDao")
    private UserDao userDao;
```

> 也可以不指定名称，则根据属性名称进行注入

> 根据类型配置（名字对不上时）

![image-20231115171520872](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115171520872.png)

## Spring全注解开发

> 不用再配置文件

用配置类来替代配置文件

```java
@Configuration
@ComponentScan("com.wjord.resouce")
public class SpringConfig {
}
```

```java
ApplicationContext context = new 
              AnnotationConfigApplicationContext(SpringConfig.class);
```



原理-手写IoC
