package net.codejava.security;

import net.codejava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    return new UserDetailsServiceImpl();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/", "/login", "/oauth/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin().permitAll()
        .loginPage("/login")
        .usernameParameter("email")
        .passwordParameter("pass")
        .defaultSuccessUrl("/list")
        .and()
        .oauth2Login()
        .loginPage("/login")
        .userInfoEndpoint()
        .userService(oauthUserService)
        .and()
        .successHandler((request, response, authentication) -> {
          System.out.println("AuthenticationSuccessHandler invoked");
          System.out.println("Authentication name: " + authentication.getName());
          CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();

          userService.processOAuthPostLogin(oauthUser.getEmail());

          response.sendRedirect("/list");
        })
        //.defaultSuccessUrl("/list")
        .and()
        .logout().logoutSuccessUrl("/").permitAll()
        .and()
        .exceptionHandling().accessDeniedPage("/403")
    ;
  }

  @Autowired
  private CustomOAuth2UserService oauthUserService;

  @Autowired
  private UserService userService;
}
