package com.luoshupeng.multidatasource.configurer;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author min
 * Created by luoshupeng on 2018-03-20 10:42
 */
@Configuration
public class DataSourceConfigurer {

  /**
   * 第一种方法
   */
  @Primary
  @Bean(name = "primaryDataSourceProperties")
  @Qualifier("primaryDataSourceProperties")
  @ConfigurationProperties(prefix = "spring.datasource.primary")
  public DataSourceProperties primaryDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Primary
  @Bean(name = "primaryDataSource")
  @Qualifier("primaryDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.primary")
  public DataSource primaryDataSource() {
    return primaryDataSourceProperties().initializeDataSourceBuilder().build();
  }

  /**
   * 第二种方法
   */
  @Bean(name = "secondaryDataSource")
  @Qualifier("secondaryDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.secondary")
  public DataSource secondaryDataSource() {
    //return DataSourceBuilder.create().type(HikariDataSource.class).build();
    return DataSourceBuilder.create().build();
  }
}
