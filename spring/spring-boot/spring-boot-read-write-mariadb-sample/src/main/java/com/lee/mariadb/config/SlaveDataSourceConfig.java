package com.lee.mariadb.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @author bruce
 */
@Configuration
@MapperScan(basePackages = "com.plf.mariadb.mapper.slave", sqlSessionTemplateRef = "slaveSqlSessionTemplate")
public class SlaveDataSourceConfig {

  @Bean
  @ConfigurationProperties("spring.datasource.slave")
  public DataSource slaveDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("slaveDataSource") DataSource slaveDataSource) throws Exception {
    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(slaveDataSource);
    //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:myabtis/slave/*.xml"));
    sessionFactory.setTypeAliasesPackage("com.plf.mariadb.bean");

    org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
    configuration.setMapUnderscoreToCamelCase(true);
    sessionFactory.setConfiguration(configuration);

    //分页插件 -- 这里不需要配置，不然会报错
        /*Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        //数据库
        properties.setProperty("helperDialect", "mariadb");
        //是否将参数offset作为PageNum使用
        properties.setProperty("offsetAsPageNum", "true");
        //是否进行count查询
        properties.setProperty("rowBoundsWithCount", "true");
        //是否分页合理化
        properties.setProperty("reasonable", "true");
        interceptor.setProperties(properties);
        sessionFactory.setPlugins(new Interceptor[] {interceptor});*/

    return sessionFactory.getObject();
  }

  @Bean
  public DataSourceTransactionManager slaveTransactionManager(@Qualifier("slaveDataSource") DataSource slaveDataSource) {
    return new DataSourceTransactionManager(slaveDataSource);
  }

  @Bean
  public SqlSessionTemplate slaveSqlSessionTemplate(@Qualifier("slaveSqlSessionFactory") SqlSessionFactory slaveSqlSessionFactory) throws Exception {
    return new SqlSessionTemplate(slaveSqlSessionFactory);
  }

}
