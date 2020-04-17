package com.wolf.mongodbit.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mongodb原生的访问方式。
 * @author min
 */
@Configuration
public class MyMongoCollection {

  private String dbName = "wolf";

  public MongoClient mongoClient() {
    MongoClient mongoClient = new MongoClient("localhost", 27017);
    return mongoClient;
  }

  protected String getDatabaseName() {
    return dbName;
  }

  @Bean(name = "mongoDatabase")
  public MongoDatabase mongoDatabase() throws Exception {
    return this.mongoClient().getDatabase(this.getDatabaseName());
  }
}

