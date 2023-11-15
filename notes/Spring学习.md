# **SSM框架**

# ***Spring***

## Spring Framework系统架构

![截屏2023-11-12 20.15.49](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/%E6%88%AA%E5%B1%8F2023-11-12%2020.15.49.png)

##  核心概念

### IoC(Inversion of Control)控制反转

使用对象时，由主动new产生对象转换位由外部提供对象， 此过程中对象创建控制权由程序转移到外部，此思想称为控制反转

### Spring技术对IoC思想进行了实现

Spring提供了一个容器，称为IoC容器，用来充当IoC思想中的外部

IoC容器负责对象的创建、初始化等一系列工作，被创建或被管理的对象在IoC容器中称为Bean

### IoC入门案例

> 导入 spring的坐标spring-context

```xml
<dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.3.13</version>
        </dependency>
```

> 配置bean
>
> id属性标示给bean起名字
>
> class属性表示给bean定义类型

```xml
<bean id="bookDao" class="org.example.BookDao"/>

    <bean id="bookService" class="org.example.BookService"/>
```

> 获取IoC容器

> 获取bean

```java
public interface BookDao {
    public void save();
}
```

```java
public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao bookDao = (BookDao) ctx.getBean("BookDao");
        bookDao.save();
    }
}
```



### DI的入门案例

> 删除业务层中使用new的方式创建的dao对象

> 提供对应的set方法

```java
public class BookServiceImpl {
    private BookDao bookDao;
    public void save() {
        System.out.println("BookServiceImpl save");
    }
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
```

> 配置service与dao的关系

> property标签表示配置当前bean的属性
>
> name属性表示配置哪一个具体的属性
>
> ref属性表示参照哪一个bean

```xml
<bean id="bookDao" class="org.example.Dao.Impl.BookDaoImpl"/>

    <bean id="bookService" class="org.example.Service.Impl.BookServiceImpl">
        <property name="bookDao" ref="bookDao"/>
    </bean>
```

