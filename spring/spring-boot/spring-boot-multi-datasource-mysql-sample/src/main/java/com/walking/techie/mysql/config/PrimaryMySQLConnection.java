package com.walking.techie.mysql.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
  basePackages = {"com.walking.techie.common.repository", "com.walking.techie.primary.repository"},
  entityManagerFactoryRef = "primaryEntityManager",
  transactionManagerRef = "primaryTransactionManager"
)
@ConfigurationProperties(prefix = "primary.mysql")
public class PrimaryMySQLConnection extends AbstractMySQLConfig {

  @Primary
  @Override
  @Bean(name = "primaryDataSource")
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setUrl(getUrl());
    dataSource.setUsername(getUsername());
    dataSource.setPassword(getPassword());
    return dataSource;
  }

  @Primary
  @Bean(name = "primaryEntityManager")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      EntityManagerFactoryBuilder builder) {
    return builder
        .dataSource(dataSource())
        .persistenceUnit("test")
        .packages("com.walking.techie.model.primary")
        .build();
  }

  @Primary
  @Bean(name = "primaryTransactionManager")
  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }
}
