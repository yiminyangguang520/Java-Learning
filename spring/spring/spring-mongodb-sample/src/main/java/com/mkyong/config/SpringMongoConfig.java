package com.mkyong.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Spring MongoDB configuration file
 * @author litz-a
 */
@Configuration
public class SpringMongoConfig {

   @Bean
   public MongoTemplate mongoTemplate() throws Exception {

    MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient("127.0.0.1"), "yourdb");
    return mongoTemplate;

  }

}