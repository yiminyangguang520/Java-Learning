package com.shihuc.dbconn.sourceconfig.mysql;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author litz-a
 */
@Configuration
@MapperScan(basePackages = "com.shihuc.dbconn.dao.mysql")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class MysqlDataSourceConfig {

  @Value("${mysql.driver}")
  private String driver;

  @Value("${mysql.url}")
  private String url;

  @Value("${mysql.username}")
  private String username;

  @Value("${mysql.password}")
  private String password;

  @Bean(name = "mysqlds")
  public DataSource mysql() {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(driver);
    ds.setUrl(url);
    ds.setUsername(username);
    ds.setPassword(password);
    return ds;
  }

  @Autowired
  @Qualifier("mysqlds")
  private DataSource mysqlds;

  @Bean
  public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
    final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(mysqlds);
    return sessionFactory.getObject();
  }
}
