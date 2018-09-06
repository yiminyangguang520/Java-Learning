package cn.merryyou.sso.server;

import cn.merryyou.sso.security.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * spring Security配置 Created on 2017/12/26.
 *
 * @author zlf
 * @since 1.0
 */
@Order(1)
@Configuration
public class SsoSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  @Qualifier("ssoUserDetailsService")
  private UserDetailsService userDetailsService;

  @Autowired
  private CustomAuthenticationProvider customAuthenticationProvider;

  @Bean
  @Override
  public AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public LogoutSuccessHandler logoutSuccessHandler() {
    return new CustomLogoutSuccessHandler();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.formLogin()
        .loginPage("/authentication/require")
        .loginProcessingUrl("/authentication/form")
        .and()
        .authorizeRequests()
        .antMatchers("/authentication/require",
            "/authentication/form",
            "/**/*.js",
            "/**/*.css",
            "/**/*.jpg",
            "/**/*.png",
            "/**/*.woff2",
            "/oauth/*",
            "/user/*",
            "/login",
            "/exit"
        )
        .permitAll()
        .anyRequest().authenticated()
        .and()
        .csrf().disable()
        .logout()
        .logoutUrl("/exit")
        .deleteCookies("JSESSIONID")
        .invalidateHttpSession(false)
        .logoutSuccessHandler(logoutSuccessHandler())
         ;
//        http.formLogin().and().authorizeRequests().anyRequest().authenticated();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    auth.authenticationProvider(customAuthenticationProvider);
  }
}
