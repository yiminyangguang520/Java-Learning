Spring provides two types of containers: 

– **BeanFactory**: the root interface of Spring IoC container

– **ApplicationContext**: child interface of **BeanFactory**, provides Spring AOP features. It has some useful implementations: 

+ **AnnotationConfigApplicationContext**: for standalone java applications and in case of using annotations for Configuration. 
+ **ClassPathXmlApplicationContext**: If using Configuration XML File in standalone application, we do not need to provide the full path of the XML file. 
+ **FileSystemXmlApplicationContext**: This is similar to  ClassPathXmlApplicationContext but Configuration XML File can be loaded  from anywhere in the file system, so we must provide full path. 
+ **AnnotationConfigWebApplicationContext** and **XmlWebApplicationContext**: for web applications.

