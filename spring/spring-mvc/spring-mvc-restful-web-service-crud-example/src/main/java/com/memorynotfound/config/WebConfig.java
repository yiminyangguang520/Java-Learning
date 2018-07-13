package com.memorynotfound.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author litz-a
 */
@EnableWebMvc
@Configuration
@ComponentScan("com.memorynotfound")
public class WebConfig extends WebMvcConfigurerAdapter {

}
