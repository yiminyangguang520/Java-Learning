package com.packtpub.springsecurity.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author litz-a
 */
@Configuration
@Import({SecurityConfig.class, DataSourceConfig.class})
public class JavaConfig {

}
