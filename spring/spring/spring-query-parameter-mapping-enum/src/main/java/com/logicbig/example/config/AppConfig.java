package com.logicbig.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author litz-a
 */
@EnableWebMvc
@ComponentScan(basePackages = { "com.logicbig.example" })
public class AppConfig {

}