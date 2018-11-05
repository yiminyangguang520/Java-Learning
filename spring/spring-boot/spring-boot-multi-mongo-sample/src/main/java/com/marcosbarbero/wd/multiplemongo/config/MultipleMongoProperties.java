package com.marcosbarbero.wd.multiplemongo.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Marcos Barbero
 */
@Data
@ConfigurationProperties(prefix = "mongodb")
public class MultipleMongoProperties {

  private MongoProperties primary = new MongoProperties();
  private MongoProperties secondary = new MongoProperties();
}
