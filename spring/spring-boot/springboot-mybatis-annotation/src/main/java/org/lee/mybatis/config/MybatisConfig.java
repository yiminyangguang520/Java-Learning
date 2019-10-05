package org.lee.mybatis.config;

import com.github.pagehelper.PageHelper;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

//@Configuration
//@EnableTransactionManagement
public class MybatisConfig implements TransactionManagementConfigurer {

  private DataSource dataSource;

  @Autowired
  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Autowired
  private SqlSessionFactory sqlSessionFactory;

  @Override
  public PlatformTransactionManager annotationDrivenTransactionManager() {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean(name = "sqlSessionFactory")
  public SqlSessionFactory sqlSessionFactoryBean(PageHelper pageHelper) {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(dataSource);
    //自定义数据库配置的时候，需要将pageHelper的bean注入到Plugins中，如果采用系统默认的数据库配置，则只需要定义pageHelper的bean，会自动注入。
    bean.setPlugins(new Interceptor[]{pageHelper});

    try {
      return bean.getObject();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }


  @Bean
  public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }

  @Bean
  public PageHelper pageHelper() {
    PageHelper pageHelper = new PageHelper();
    Properties p = new Properties();
    p.setProperty("rowBoundsWithCount", "true");
    p.setProperty("offsetAsPageNum", "true");
    p.setProperty("reasonable", "true");
    p.setProperty("dialect", "mysql");
    pageHelper.setProperties(p);
    return pageHelper;
  }
}
