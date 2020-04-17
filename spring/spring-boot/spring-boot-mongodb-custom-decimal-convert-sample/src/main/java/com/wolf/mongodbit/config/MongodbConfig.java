package com.wolf.mongodbit.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.wolf.mongodbit.converter.BigDecimalToDecimal128Converter;
import com.wolf.mongodbit.converter.Decimal128ToBigDecimalConverter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * 自定义的配置类，配置mongodb访问对象MongoTemplate，将定义的BigDecimal和Decimal128的自动转换器，注册到转换器。
 * @author min
 */
@Configuration
public class MongodbConfig extends AbstractMongoConfiguration {

  private String dbName = "wolf";

  @Override
  public MongoClient mongoClient() {
    MongoClient mongoClient = new MongoClient();
    return mongoClient;
  }

  @Override
  protected String getDatabaseName() {
    return dbName;
  }

  @Override
  @Bean
  public MappingMongoConverter mappingMongoConverter() throws Exception {
    DefaultDbRefResolver dbRefResolver = new DefaultDbRefResolver(this.dbFactory());
    MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, this.mongoMappingContext());
    List<Object> list = new ArrayList<>();
    list.add(new BigDecimalToDecimal128Converter());
    list.add(new Decimal128ToBigDecimalConverter());
    converter.setCustomConversions(new MongoCustomConversions(list));
    return converter;
  }

  @Bean
  public MongoDbFactory dbFactory() throws Exception {
    return new SimpleMongoDbFactory(new MongoClientURI("mongodb://localhost:27017/wolf"));
  }

  @Override
  @Bean
  public MongoMappingContext mongoMappingContext() {
    MongoMappingContext mappingContext = new MongoMappingContext();
    return mappingContext;
  }

  @Override
  @Bean
  public MongoTemplate mongoTemplate() throws Exception {
    return new MongoTemplate(this.dbFactory(), this.mappingMongoConverter());
  }
}
