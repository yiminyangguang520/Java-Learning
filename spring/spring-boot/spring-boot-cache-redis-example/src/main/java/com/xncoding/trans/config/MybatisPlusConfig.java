package com.xncoding.trans.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import javax.annotation.Resource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MybatisPlus配置
 *
 * @author xiongneng
 * @since 2017/5/20 21:58
 */
@Configuration
@EnableTransactionManagement(order = 2)
@MapperScan(basePackages = {"com.xncoding.trans.dao.repository"})
public class MybatisPlusConfig {

  @Resource
  private DruidProperties druidProperties;

  /**
   * 单数据源连接池配置
   */
  @Bean
  public DruidDataSource singleDatasource() {
    DruidDataSource dataSource = new DruidDataSource();
    druidProperties.config(dataSource);
    return dataSource;
  }

  /**
   * mybatis-plus分页插件
   */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    return new PaginationInterceptor();
  }
}
