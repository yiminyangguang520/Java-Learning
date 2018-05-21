package com.onlinetutorialspoint.config;

import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author litz-a
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.onlinetutorialspoint.repository.db1"},
    entityManagerFactoryRef = "db1EntityManager",
    transactionManagerRef = "db1TransactionManager")
public class DB1_DataSource {

  @Autowired
  private Environment env;

  @Bean
  @Primary
  public LocalContainerEntityManagerFactoryBean db1EntityManager() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(db1Datasource());
    em.setPackagesToScan(new String[]{"com.onlinetutorialspoint.model.db1"});
    em.setPersistenceUnitName("db1EntityManager");
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    HashMap<String, Object> properties = new HashMap<>(2);
    properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
    properties.put("hibernate.show-sql", env.getProperty("jdbc.show-sql"));
    em.setJpaPropertyMap(properties);
    return em;
  }

  @Bean
  @Primary
  public PlatformTransactionManager db1TransactionManager() {

    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(db1EntityManager().getObject());
    return transactionManager;
  }

  @Bean
  @Primary
  public DataSource db1Datasource() {

    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("jdbc.driver-class-name"));
    dataSource.setUrl(env.getProperty("db1.datasource.url"));
    dataSource.setUsername(env.getProperty("db1.datasource.username"));
    dataSource.setPassword(env.getProperty("db1.datasource.password"));

    return dataSource;
  }
}


