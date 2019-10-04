package com.lee.mybatis.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author bruce
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {DataSourceConstants.BASE_PACKAGES})
public class DataSourceConfiguration {

  @Value("${druid.type}")
  private Class<? extends DataSource> dataSourceType;

  @Primary
  @Bean(name = "masterDataSource")
  @ConfigurationProperties(prefix = "druid.master")
  public DataSource masterDataSource() {
    return DataSourceBuilder.create().type(dataSourceType).build();
  }

  @Bean(name = "slaveDataSource")
  @ConfigurationProperties(prefix = "druid.slave")
  public DataSource slaveDataSource() {
    return DataSourceBuilder.create().type(dataSourceType).build();
  }

}
