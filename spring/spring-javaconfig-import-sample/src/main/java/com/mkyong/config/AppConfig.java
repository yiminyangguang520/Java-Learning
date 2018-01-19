package com.mkyong.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author litz-a
 */
@Configuration
@Import({CustomerConfig.class, SchedulerConfig.class})
public class AppConfig {

}