package com.walking.techie.mysql.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
  basePackages = {"com.walking.techie.secondary.repository"},
  entityManagerFactoryRef = "secondaryEntityManager",
  transactionManagerRef = "secondaryTransactionManager"
)
@ConfigurationProperties(prefix = "secondary.mysql")
public class SecondaryMySQLConnection extends AbstractMySQLConfig {

  @Override
  @Bean(name = "secondaryDataSource")
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setUrl(getUrl());
    dataSource.setUsername(getUsername());
    dataSource.setPassword(getPassword());
    return dataSource;
  }

  @Bean(name = "secondaryEntityManager")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      EntityManagerFactoryBuilder builder) {
    return builder
        .dataSource(dataSource())
        .persistenceUnit("springbatch")
        .packages("com.walking.techie.model.secondary")
        .build();
  }

  @Bean(name = "secondaryTransactionManager")
  public PlatformTransactionManager transactionManager(
      @Qualifier("secondaryEntityManager") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }
}
