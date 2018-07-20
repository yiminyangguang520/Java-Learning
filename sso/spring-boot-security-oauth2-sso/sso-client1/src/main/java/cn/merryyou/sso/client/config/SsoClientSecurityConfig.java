package cn.merryyou.sso.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author litz-a
 */
@Configuration
@Order(300)
public class SsoClientSecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${auth-server}/exit")
  private String logoutUrl;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .logout()
        .deleteCookies("JSESSIONID")
        .logoutSuccessUrl(logoutUrl)
        .and().authorizeRequests().anyRequest().authenticated();
  }
}