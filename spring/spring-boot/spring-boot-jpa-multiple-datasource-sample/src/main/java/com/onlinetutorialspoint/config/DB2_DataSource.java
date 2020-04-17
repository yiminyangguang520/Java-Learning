package com.onlinetutorialspoint.config;

import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author min
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.onlinetutorialspoint.repository.db2"},
    entityManagerFactoryRef = "db2EntityManager",
    transactionManagerRef = "db2TransactionManager")
public class DB2_DataSource {

  @Autowired
  private Environment env;

  @Bean
  public LocalContainerEntityManagerFactoryBean db2EntityManager() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(db2Datasource());
    em.setPackagesToScan(new String[]{"com.onlinetutorialspoint.model.db2"});
    em.setPersistenceUnitName("db2EntityManager");
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    HashMap<String, Object> properties = new HashMap<>(2);
    properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
    properties.put("hibernate.show-sql", env.getProperty("jdbc.show-sql"));
    em.setJpaPropertyMap(properties);
    return em;
  }

  @Bean
  public DataSource db2Datasource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("jdbc.driver-class-name"));
    dataSource.setUrl(env.getProperty("db2.datasource.url"));
    dataSource.setUsername(env.getProperty("db2.datasource.username"));
    dataSource.setPassword(env.getProperty("db2.datasource.password"));

    return dataSource;
  }

  @Bean
  public PlatformTransactionManager db2TransactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(db2EntityManager().getObject());
    return transactionManager;
  }
}