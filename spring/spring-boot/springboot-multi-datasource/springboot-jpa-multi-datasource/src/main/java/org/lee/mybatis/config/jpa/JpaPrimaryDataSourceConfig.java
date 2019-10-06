package org.lee.mybatis.config.jpa;

import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.lee.mybatis.config.PrimaryDataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author bruce
 */
@Configuration
@Import({PrimaryDataSourceConfig.class})
@EnableJpaRepositories(
  entityManagerFactoryRef = "primaryEntityManagerFactory",
  transactionManagerRef = "primaryTransactionManager",
  // 设置Repository所在位置
  basePackages = {JpaPrimaryDataSourceConfig.REPOSITORY_PACKAGE})
public class JpaPrimaryDataSourceConfig {

  /**
   * 实体类所在包
   */
  private final static String ENTITY_PACKAGE = "org.lee.mybatis.model.primary";
  /**
   * 数据访问层类所在包
   */
  final static String REPOSITORY_PACKAGE = "org.lee.mybatis.repository.primary";
  /**
   * 持久化单元
   */
  private final static String PERSISTENCE_UNIT = "persistenceUnit";

  private final DataSource primaryDataSource;
  private final JpaProperties jpaProperties;
  private final HibernateProperties hibernateProperties;

  @Autowired
  public JpaPrimaryDataSourceConfig(JpaProperties jpaProperties,
    @Qualifier("primaryDataSource") DataSource primaryDataSource,
    HibernateProperties hibernateProperties) {
    this.jpaProperties = jpaProperties;
    this.primaryDataSource = primaryDataSource;
    this.hibernateProperties = hibernateProperties;
  }

  @Primary
  @Bean(name = "primaryEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder) {
    return builder.dataSource(primaryDataSource)
      // 数据源相关配置信息
      .properties(getDataSourceProperties(primaryDataSource))
      // 设置实体类所在位置
      .packages(ENTITY_PACKAGE)
      .persistenceUnit(PERSISTENCE_UNIT)
      .build();
  }

  @Primary
  @Bean(name = "primaryEntityManager")
  public EntityManager primaryEntityManager(EntityManagerFactoryBuilder builder) {
    return primaryEntityManagerFactory(builder).getObject().createEntityManager();
  }

  @Primary
  @Bean(name = "primaryTransactionManager")
  public PlatformTransactionManager primaryTransactionManager(EntityManagerFactoryBuilder builder) {
    EntityManagerFactory entityManagerFactory = primaryEntityManagerFactory(builder).getObject();
    return new JpaTransactionManager(entityManagerFactory);
  }

  private Map<String, Object> getDataSourceProperties(DataSource dataSource) {
    return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
  }

}
