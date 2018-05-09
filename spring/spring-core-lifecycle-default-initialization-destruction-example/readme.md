When working on a project with many team members, it is good to have some conventions. One of this conventions could be that you agree to use the same method name for initialization and destruction methods. You can globally configure your default initialization and or destruction methods in spring configuration. This way you don’t need to add an init-method or destroy-method on every single spring bean.

  

## Adding Initialization and Destruction Methods

This bean has two special methods, The `init()` will be used to initialize this bean. The `destroy()` method will be used to clean up some resources before the bean is destroyed. These methods must have a void no-argument method signature to be eligible.

```
package com.memorynotfound.spring.core.lifecycle;

public class Course {

    private String name;

    public void init(){
        System.out.println("- - - initializing course bean using default-init-method");
        System.out.println("name: " + name);
    }

    public void destroy(){
        System.out.println("- - - destroying course bean using default-destroy-method");
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

## Globally Configure default-init-method and default-destroy-method

In the top level **<beans/>** element you can configure the **default-init-method** and **default-destroy-method**. These attributes take a name of the default method that has a void no-argument method signature which is called upon initialization and destruction respectively. **It is noticeable to indicate that these methods are only invoked on spring managed singleton beans**.

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd"
        default-init-method="init" default-destroy-method="destroy">

    <bean class="com.memorynotfound.spring.core.lifecycle.Course">
        <property name="name" value="default init and destroy methods"/>
    </bean>

</beans>
```

## Running the Application

When callling the `close();` method of the `ConfigurableApplicationContext`, all the resources will be released and the destruction methods of all cached singleton beans will be called.

```
package com.memorynotfound.spring.core.lifecycle;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {

    public static void main(String... args) throws InterruptedException {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");
        context.close();
    }
}
```

## Output

Notice that the properties are set before the initialization method is called.

```
- - - initializing course bean using default-init-method
name: default init and destroy methods
- - - destroying course bean using default-destroy-method
```

## References

- [Default initialization and destroy methods Doc](https://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#beans-factory-lifecycle-default-init-destroy-methods)