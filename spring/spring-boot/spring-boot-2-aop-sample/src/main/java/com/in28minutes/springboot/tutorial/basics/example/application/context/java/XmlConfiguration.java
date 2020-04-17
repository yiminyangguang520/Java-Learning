package com.in28minutes.springboot.tutorial.basics.example.application.context.java;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author min
 */
@Configuration
@ImportResource({"classpath*:applicationContext.xml"})
public class XmlConfiguration {

}
