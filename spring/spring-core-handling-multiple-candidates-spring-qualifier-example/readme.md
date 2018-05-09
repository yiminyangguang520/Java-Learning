[Handling multiple autowire candidates with @Primary](https://memorynotfound.com/handling-multiple-autowire-candidates-with-spring-primary/) is effective when only one primary candidate can be determined for multiple autowire candidates. When you need more control over the selection process, you can use spring **@Qualifier** annotation. The `@Qualifier` annotation allows you to associate a given name with a specific bean type. Thus autowiring by type and by name, to narrow the autowire dependency candidates to only one.

  

## Handle Multiple Autowire dependencies with @Qualifier

The `@Qualifier` annotation may be used on a field, method or parameter as a qualifier for candidate beans when autowiring. This makes the autowiring by type and by name. Thus reducing and specifying the correct type for autowire dependency injection. In this example both `MysqlMessageRepository` and `InMemoryMessageRepository` beans are injected in the `SellPhone` bean.

```java
package com.memorynotfound.spring.core.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SellPhone {

    @Autowired
    @Qualifier(value = "mysqlMessageRepository")
    private MessageRepository mysqlMessageRepository;

    @Autowired
    @Qualifier(value = "inMemoryMessageRepository")
    private MessageRepository inMemoryMessageRepository;

    public void sendMessage(){
        mysqlMessageRepository.save();
        inMemoryMessageRepository.save();
    }
}
```

We are creating two implementations of this interface, which we are autowiring in a spring bean.

```java
package com.memorynotfound.spring.core.autowired;

public interface MessageRepository {

    void save();
}
```

The `@Repository` is a special marker annotation. When component scanning is enabled, spring will automatically pick it up and create a bean definition to make it available for autowiring.

```java
package com.memorynotfound.spring.core.autowired;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryMessageRepository implements MessageRepository{

    @Override
    public void save() {
        System.out.println("saving in memory");
    }
}
```

This is the second implementation.

```java
package com.memorynotfound.spring.core.autowired;

import org.springframework.stereotype.Repository;

@Repository
public class MysqlMessageRepository implements MessageRepository{

    @Override
    public void save() {
        System.out.println("saving in mysql");
    }
}
```

## Enable Auto Component Scanning

We enable auto component scanning by registering the **<context:component-scan/>** element inside the top level **<beans/>** element. We provide a package name which spring will recursively scan for other components.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd">

    <context:component-scan base-package="com.memorynotfound"/>

</beans>
```

## Testing The Application

We bootstrap the application by loading the spring xml configuration file using the `ClassPathXmlApplicationContext`. Afterwards we get a reference of the `SellPhone` bean on which we invoke the `save()` method.

```java
package com.memorynotfound.spring.core.autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {

    public static void main(String... args) {
        ApplicationContext xmlContext = new ClassPathXmlApplicationContext("app-config.xml");
        SellPhone sellPhone = xmlContext.getBean(SellPhone.class);
        sellPhone.sendMessage();
    }
}
```

This is the produced output of the previous code. As you can see, both autowired dependencies are injected.

```
saving in mysql
saving in memory
```

## References

- [@Qualifier JavaDoc](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/beans/factory/annotation/Qualifier.html)