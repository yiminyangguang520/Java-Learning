1、Address类上添加@Lazy注解,Person类中的address成员变量添加@Lazy注解,输出信息如下:

```
    Person initialized
    Application context loaded
    Getting Person Bean
    Waiting...
    Getting the address
    Address initialized
    Address{}.toString()
```


2、Address类上添加@Lazy注解,Person类中的address成员变量去掉@Lazy注解,输出信息如下:

```
    Person initialized
    Address initialized
    Application context loaded
    Getting Person Bean
    Waiting...
    Getting the address
    Address{}.toString()
```


3、Address类上去掉@Lazy注解,Person类中的address成员变量去掉@Lazy注解,输出信息如下:

```
    Address initialized
    Person initialized
    Application context loaded
    Getting Person Bean
    Waiting...
    Getting the address
    Address{}.toString()
```

