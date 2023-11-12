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

   
