package com.devglan.config;

import com.devglan.model.UserDetails;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author min
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "userEntityManager", transactionManagerRef = "userTransactionManager",
    basePackages = "com.devglan.user.dao")
public class UserDBConfig {

  @Bean
  @ConfigurationProperties(prefix = "spring.user.datasource")
  public DataSource postgresqlDataSource() {
    return DataSourceBuilder
        .create()
        .build();
  }

  @Bean(name = "userEntityManager")
  public LocalContainerEntityManagerFactoryBean postgresqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
    return builder
        .dataSource(postgresqlDataSource())
        .properties(hibernateProperties())
        .packages(UserDetails.class)
        .persistenceUnit("userPU")
        .build();
  }

  @Bean(name = "userTransactionManager")
  public PlatformTransactionManager postgresqlTransactionManager(@Qualifier("userEntityManager") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }

  private Map<String, Object> hibernateProperties() {

    Resource resource = new ClassPathResource("hibernate.properties");

    try {
      Properties properties = PropertiesLoaderUtils.loadProperties(resource);

      return properties.entrySet().stream()
          .collect(Collectors.toMap(
              e -> e.getKey().toString(),
              e -> e.getValue())
          );
    } catch (IOException e) {
      return new HashMap<>(2);
    }
  }
}
