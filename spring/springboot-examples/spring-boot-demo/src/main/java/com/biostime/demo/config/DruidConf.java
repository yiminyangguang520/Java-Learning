package com.biostime.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.mama100.platform.integr.configure.config.JdbcConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * DruidConfig
 *
 * @author 12465
 * @version 1.0
 * @createDate 2016/7/18 10:04
 */
@Component
public class DruidConf {

    @Autowired
    private JdbcConfig jdbcConfig;

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        //设置druid web端访问url
        reg.addUrlMappings(jdbcConfig.getDruidUrlMappings());
        //设置允许访问的IP
        if (!StringUtils.isEmpty(jdbcConfig.getDruidAllow())) {
            reg.addInitParameter("allow", jdbcConfig.getDruidAllow());
        }
        //设置拒绝访问的IP
        if (!StringUtils.isEmpty(jdbcConfig.getDruidDeny())) {
            reg.addInitParameter("deny", jdbcConfig.getDruidDeny());
        }
        //控制端登录账号密码
        reg.addInitParameter("loginUsername", jdbcConfig.getDruidLoginUsername());
        reg.addInitParameter("loginPassword", jdbcConfig.getDruidLoginPassword());
        return reg;
    }

    @Bean
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        //连接池驱动
        druidDataSource.setDriverClassName(jdbcConfig.getDriver());
        //连接URL
        druidDataSource.setUrl(jdbcConfig.getUrl());
        //数据库访问用户名
        druidDataSource.setUsername(jdbcConfig.getUser());
        //数据库访问用户密码
        druidDataSource.setPassword(jdbcConfig.getPassword());
        //初始化连接数量
        druidDataSource.setInitialSize(jdbcConfig.getDruidInitialSize());
        //最大并发连接数
        druidDataSource.setMaxActive(jdbcConfig.getDruidMaxActive());
        //最小空闲连接数
        druidDataSource.setMinIdle(jdbcConfig.getDruidMinIdle());
        //配置获取连接等待超时的时间
        druidDataSource.setMaxWait(jdbcConfig.getDruidMaxWait());
        //超过时间限制是否回收
        druidDataSource.setRemoveAbandoned(jdbcConfig.getDruidRemoveAbandoned());
        //超过时间限制多长
        druidDataSource.setRemoveAbandonedTimeout(jdbcConfig.getDruidRemoveAbandonedTimeout());
        //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        druidDataSource.setTimeBetweenEvictionRunsMillis(jdbcConfig.getDruidTimeBetweenEvictionRunsMillis());
        //配置一个连接在池中最小生存的时间，单位是毫秒
        druidDataSource.setMinEvictableIdleTimeMillis(jdbcConfig.getDruidMinEvictableIdleTimeMillis());
        //用来检测连接是否有效的sql，要求是一个查询语句
        druidDataSource.setValidationQuery(jdbcConfig.getDruidValidationQuery());
        //申请连接的时候检测
        druidDataSource.setTestWhileIdle(jdbcConfig.getDruidTestWhileIdle());
        //申请连接时执行validationQuery检测连接是否有效，配置为true会降低性能
        druidDataSource.setTestOnBorrow(jdbcConfig.getDruidTestOnBorrow());
        //归还连接时执行validationQuery检测连接是否有效，配置为true会降低性能
        druidDataSource.setTestOnReturn(jdbcConfig.getDruidTestOnReturn());
        //打开PSCache，并且指定每个连接上PSCache的大小
        druidDataSource.setPoolPreparedStatements(jdbcConfig.getDruidPoolPreparedStatements());
        //打开PSCache，并且指定最大连接PSCache的数量
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(jdbcConfig.getDruidMaxPoolPreparedStatementPerConnectionSize());
        try {
            /*
            *   属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：
                监控统计用的filter:stat
                日志用的filter:log4j
                防御SQL注入的filter:wall
            * */
            druidDataSource.setFilters(jdbcConfig.getDruidFilters());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        //设置WebStatFilter
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns(jdbcConfig.getDruidUrlPatterns());
        filterRegistrationBean.addInitParameter("exclusions", jdbcConfig.getDruidExclusions());
        return filterRegistrationBean;
    }
}
