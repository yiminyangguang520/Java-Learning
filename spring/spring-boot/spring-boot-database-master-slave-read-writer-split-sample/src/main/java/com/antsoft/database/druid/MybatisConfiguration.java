package com.antsoft.database.druid;

import com.antsoft.database.mybatis.DbContextHolder;
import com.antsoft.database.mybatis.ReadWriteSplitRoutingDataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Jason
 * @date 2017/3/6
 */
@Configuration
@AutoConfigureAfter({DataSourceConfiguration.class})
public class MybatisConfiguration extends MybatisAutoConfiguration {

  private static Log logger = LogFactory.getLog(MybatisConfiguration.class);

  @Autowired
  @Qualifier(value = "masterDataSource")
  private DataSource masterDataSource;

  @Autowired
  @Qualifier(value = "slaveDataSource")
  private DataSource slaveDataSource;


  public MybatisConfiguration(MybatisProperties properties,
      ObjectProvider<Interceptor[]> interceptorsProvider,
      ResourceLoader resourceLoader,
      ObjectProvider<DatabaseIdProvider> databaseIdProvider,
      ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
    super(properties, interceptorsProvider, resourceLoader, databaseIdProvider, configurationCustomizersProvider);
  }


  @Bean(name = "roundRobinDataSouceProxy")
  public AbstractRoutingDataSource roundRobinDataSouceProxy() {
    ReadWriteSplitRoutingDataSource proxy = new ReadWriteSplitRoutingDataSource();

    Map<Object, Object> targetDataResources = new HashMap<>(2);
    targetDataResources.put(DbContextHolder.DbType.MASTER, masterDataSource);
    targetDataResources.put(DbContextHolder.DbType.SLAVE, slaveDataSource);

    proxy.setDefaultTargetDataSource(masterDataSource);
    proxy.setTargetDataSources(targetDataResources);
    proxy.afterPropertiesSet();
    return proxy;
  }

  @Bean
  @Override
  public SqlSessionFactory sqlSessionFactory(@Qualifier("roundRobinDataSouceProxy") DataSource dataSource) throws Exception {
    logger.info("-------------------- 重载父类 sqlSessionFactory init ---------------------");
    return super.sqlSessionFactory(roundRobinDataSouceProxy());
  }

//  @Override
//  public void setEnvironment(Environment environment) {
//    this.propertyResolver = new RelaxedPropertyResolver(environment, "mybatis.");
//  }
//
//  @Bean
//  @ConditionalOnMissingBean
//  public SqlSessionFactory sqlSessionFactory() {
//    try {
//      SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//      sessionFactoryBean.setDataSource(roundRobinDataSouceProxy());
//      sessionFactoryBean.setTypeAliasesPackage(propertyResolver.getProperty("typeAliasesPackage"));
//      sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(
//          propertyResolver.getProperty("mapperLocations")
//      ));
//      sessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(
//          propertyResolver.getProperty("configLocation")
//      ));
//      return sessionFactoryBean.getObject();
//    } catch (Exception e) {
//      logger.warn("Could not confiure mybatis session factory");
//      return null;
//    }
//  }
}
