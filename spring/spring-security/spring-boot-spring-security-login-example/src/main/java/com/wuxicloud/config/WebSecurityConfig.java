package com.wuxicloud.config;

import com.wuxicloud.dao.UserRepository;
import com.wuxicloud.model.User;
import com.wuxicloud.security.SecurityUser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * Created by EalenXie on 2018/1/11.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

  @Override
  protected void configure(HttpSecurity http) throws Exception { //配置策略
    http.csrf().disable();
    //logoutSuccessUrl("/login")//使用了logoutSuccessHandler实现了这个功能
    http.authorizeRequests().
        antMatchers("/static/**").permitAll().anyRequest().authenticated().
        and().formLogin().loginPage("/login").permitAll().successHandler(loginSuccessHandler()).
        and().logout().permitAll().invalidateHttpSession(true).
        deleteCookies("JSESSIONID").logoutSuccessHandler(logoutSuccessHandler()).
        and().sessionManagement().maximumSessions(10).expiredUrl("/login");
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    auth.eraseCredentials(false);
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() { //密码加密
    return new BCryptPasswordEncoder(4);
  }


  @Bean
  public LogoutSuccessHandler logoutSuccessHandler() { //登出处理
    return (httpServletRequest, httpServletResponse, authentication) -> {
      try {
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        logger.info("USER : " + user.getUsername() + " LOGOUT SUCCESS !  ");
      } catch (Exception e) {
        logger.info("LOGOUT EXCEPTION , e : " + e.getMessage());
      }
      httpServletResponse.sendRedirect("/login");
    };
  }

  @Bean
  public SavedRequestAwareAuthenticationSuccessHandler loginSuccessHandler() { //登入处理
    return new SavedRequestAwareAuthenticationSuccessHandler() {
      @Override
      public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User userDetails = (User) authentication.getPrincipal();
        logger.info(String.format("USER : %s LOGIN SUCCESS !  ", userDetails.getUsername()));
        super.onAuthenticationSuccess(request, response, authentication);
      }
    };
  }

  @Bean
  @Override
  public UserDetailsService userDetailsService() {    //用户登录实现
    return new UserDetailsService() {
      @Autowired
      private UserRepository userRepository;

      @Override
      public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if (user == null) {
          throw new UsernameNotFoundException("Username " + s + " not found");
        }
        return new SecurityUser(user);
      }
    };
  }


}
