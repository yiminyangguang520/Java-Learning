package com.marcosbarbero.wd.multiplemongo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Marcos Barbero
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.marcosbarbero.wd.multiplemongo.repository.primary",
    mongoTemplateRef = PrimaryMongoConfig.MONGO_TEMPLATE)
public class PrimaryMongoConfig {

  protected static final String MONGO_TEMPLATE = "primaryMongoTemplate";
}
