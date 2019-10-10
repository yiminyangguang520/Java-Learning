package org.lee.mybatis.config.jpa;

import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.lee.mybatis.config.SecondaryDataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author bruce
 */
@Configuration
@Import({SecondaryDataSourceConfig.class})
@EnableJpaRepositories(
  entityManagerFactoryRef = "secondaryEntityManagerFactory",
  transactionManagerRef = "secondaryTransactionManager",
  // 设置Repository所在位置
  basePackages = {JpaSecondaryDataSourceConfig.REPOSITORY_PACKAGE})
public class JpaSecondaryDataSourceConfig {

  /**
   * 实体类所在包
   */
  private final static String ENTITY_PACKAGE = "org.lee.mybatis.model.secondary";
  /**
   * 数据访问层类所在包
   */
  final static String REPOSITORY_PACKAGE = "org.lee.mybatis.repository.secondary";
  /**
   * 持久化单元
   */
  private final static String PERSISTENCE_UNIT = "persistenceUnit";

  private final DataSource secondaryDataSource;
  private final JpaProperties jpaProperties;
  private final HibernateProperties hibernateProperties;


  @Autowired
  public JpaSecondaryDataSourceConfig(JpaProperties jpaProperties,
    @Qualifier("secondaryDataSource") DataSource secondaryDataSource,
    HibernateProperties hibernateProperties) {
    this.jpaProperties = jpaProperties;
    this.secondaryDataSource = secondaryDataSource;
    this.hibernateProperties = hibernateProperties;
  }

  @Bean(name = "secondaryEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(
    EntityManagerFactoryBuilder builder) {
    return builder.dataSource(secondaryDataSource)
      // 数据源相关配置信息
      .properties(getDataSourceProperties(secondaryDataSource))
      // 设置实体类所在位置
      .packages(ENTITY_PACKAGE)
      .persistenceUnit(PERSISTENCE_UNIT)
      .build();
  }

  @Bean(name = "secondaryEntityManager")
  public EntityManager primaryEntityManager(EntityManagerFactoryBuilder builder) {
    return secondaryEntityManagerFactory(builder).getObject().createEntityManager();
  }

  @Bean(name = "secondaryTransactionManager")
  public PlatformTransactionManager secondaryTransactionManager(EntityManagerFactoryBuilder builder) {
    EntityManagerFactory entityManagerFactory = secondaryEntityManagerFactory(builder).getObject();
    return new JpaTransactionManager(entityManagerFactory);
  }

  private Map<String, Object> getDataSourceProperties(DataSource dataSource) {
    return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
  }

}
