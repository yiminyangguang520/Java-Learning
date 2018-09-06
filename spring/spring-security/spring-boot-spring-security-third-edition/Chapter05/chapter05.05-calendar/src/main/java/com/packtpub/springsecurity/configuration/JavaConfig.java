package com.packtpub.springsecurity.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Mick Knutson
 * @since chapter 01.00
 */
@Configuration
@Import({SecurityConfig.class, MongoDataInitializer.class})
@EnableMongoRepositories(basePackages = "com.packtpub.springsecurity.repository")

public class JavaConfig {

} // The end...
