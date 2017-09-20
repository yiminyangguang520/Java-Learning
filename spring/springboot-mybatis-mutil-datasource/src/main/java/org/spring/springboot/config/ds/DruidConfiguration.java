package org.spring.springboot.config.ds;

import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * druid 配置.
 *
 * 这样的方式不需要添加注解：@ServletComponentScan
 *
 * @author Administrator
 */
@Configuration
public class DruidConfiguration {

  /**
   * 注册一个StatViewServlet
   */
  @Bean
  public ServletRegistrationBean DruidStatViewServlet() {
    //org.springframework.boot.web.servlet.ServletRegistrationBean提供类的进行注册.
    ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

    //添加初始化参数：initParams

    //白名单：
    servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
    //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
    servletRegistrationBean.addInitParameter("deny", "192.168.1.73");
    //登录查看信息的账号密码.
    servletRegistrationBean.addInitParameter("loginUsername", "admin");
    servletRegistrationBean.addInitParameter("loginPassword", "123456");
    //是否能够重置数据.
    servletRegistrationBean.addInitParameter("resetEnable", "false");
    return servletRegistrationBean;
  }

  /**
   * 注册一个：filterRegistrationBean
   */
  @Bean
  public FilterRegistrationBean druidStatFilter() {

    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());

    //添加过滤规则.
    filterRegistrationBean.addUrlPatterns("/*");

    //添加不需要忽略的格式信息.
    filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
    return filterRegistrationBean;
  }

  /**
   * 监听Spring
   *  1.定义拦截器
   *  2.定义切入点
   *  3.定义通知类
   * @return
   */
//  @Bean
//  public DruidStatInterceptor druidStatInterceptor(){
//    return new DruidStatInterceptor();
//  }
//
//  @Bean
//  public JdkRegexpMethodPointcut druidStatPointcut(){
//    JdkRegexpMethodPointcut druidStatPointcut = new JdkRegexpMethodPointcut();
//    String patterns = "com.ft.*.*.service.*";
//    String patterns2 = "com.ft.*.*.mapper.*";
//    druidStatPointcut.setPatterns(patterns,patterns2);
//    return druidStatPointcut;
//  }
//
//  @Bean
//  public Advisor druidStatAdvisor() {
//    return new DefaultPointcutAdvisor(druidStatPointcut(), druidStatInterceptor());
//  }

}