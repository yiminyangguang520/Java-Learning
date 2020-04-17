package com.packtpub.springsecurity.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author min
 */
@Configuration
@Import({SecurityConfig.class, DataSourceConfig.class})
public class JavaConfig {

}
