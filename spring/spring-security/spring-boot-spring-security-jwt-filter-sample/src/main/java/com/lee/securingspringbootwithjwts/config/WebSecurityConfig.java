package com.lee.securingspringbootwithjwts.config;

import com.lee.securingspringbootwithjwts.filter.JwtAuthenticationFilter;
import com.lee.securingspringbootwithjwts.filter.JwtLoginFilter;
import com.lee.securingspringbootwithjwts.security.CustomAuthenticationProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  /**
   * 设置 HTTP 验证规则
   * @param http
   * @throws Exception
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // 关闭csrf验证
    http.csrf().disable()
        // 对请求进行认证
        .authorizeRequests()
        // 所有 / 的所有请求 都放行
        .antMatchers("/").permitAll()
        // 所有 /login 的POST请求 都放行
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        // 添加权限检测
        .antMatchers("/hello").hasAuthority("AUTH_WRITE")
        // 角色检测
        .antMatchers("/world").hasRole("ADMIN")
        // 所有请求需要身份认证
        .anyRequest().authenticated()
        .and()
        // 添加一个过滤器 所有访问 /login 的请求交给 JwtLoginFilter 来处理 这个类处理所有的JWT相关内容
        .addFilterBefore(new JwtLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
        // 添加一个过滤器验证其他请求的Token是否合法
        .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // 使用自定义身份验证组件
    auth.authenticationProvider(new CustomAuthenticationProvider());

  }
}
