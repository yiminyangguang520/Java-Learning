package com.packtpub.springsecurity.configuration;

import com.packtpub.springsecurity.authentication.CalendarUserAuthenticationProvider;
import com.packtpub.springsecurity.service.CalendarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Description;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * Spring Security Config Class
 *
 * @author min
 * @see {@link WebSecurityConfigurerAdapter}
 * @since chapter11.00
 */
@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.IGNORED_ORDER - 2)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

  @Autowired
  private CalendarUserAuthenticationProvider calendarUserAuthenticationProvider;

  /**
   * Configure AuthenticationManager.
   *
   * NOTE: Due to a known limitation with JavaConfig:
   * <a href="https://jira.spring.io/browse/SPR-13779">
   * https://jira.spring.io/browse/SPR-13779</a>
   *
   * We cannot use the following to expose a {@link UserDetailsManager}
   * <pre>
   *     http.authorizeRequests()
   * </pre>
   *
   * In order to expose {@link UserDetailsManager} as a bean, we must create  @Bean
   *
   * @param auth AuthenticationManagerBuilder
   * @throws Exception Authentication exception
   * @see {@link super.userDetailsService()}
   * @see {@link com.packtpub.springsecurity.service.DefaultCalendarService}
   */
  @Override
  public void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(calendarUserAuthenticationProvider);
  }


  /**
   * BCryptPasswordEncoder password encoder
   */
  @Bean
  @Description("Standard PasswordEncoder")
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(4);
  }


  /**
   * HTTP Security configuration
   *
   * <pre><http auto-config="true"></pre> is equivalent to:
   * <pre>
   *  <http>
   *      <form-login />
   *      <http-basic />
   *      <logout />
   *  </http>
   * </pre>
   *
   * Which is equivalent to the following JavaConfig:
   *
   * <pre>
   *     http.formLogin()
   *          .and().httpBasic()
   *          .and().logout();
   * </pre>
   *
   * @param http HttpSecurity configuration.
   * @throws Exception Authentication configuration exception
   * @see <a href="http://docs.spring.io/spring-security/site/migrate/current/3-to-4/html5/migrate-3-to-4-jc.html">
   * Spring Security 3 to 4 migration</a>
   */
  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    // Matching
    http.authorizeRequests()
        // FIXME: TODO: Allow anyone to use H2 (NOTE: NOT FOR PRODUCTION USE EVER !!! )
        .antMatchers("/admin/h2/**").permitAll()

        .antMatchers("/").permitAll()
        .antMatchers("/login/*").permitAll()
        .antMatchers("/logout").permitAll()
        .antMatchers("/signup/*").permitAll()
        .antMatchers("/errors/**").permitAll()
        .antMatchers("/admin/*").access("hasRole('ADMIN') and isFullyAuthenticated()")
        .antMatchers("/events/").hasRole("ADMIN")
        .antMatchers("/**").hasRole("USER");

    // Login
    http.formLogin()
        .loginPage("/login/form")
        .loginProcessingUrl("/login")
        .failureUrl("/login/form?error")
        .usernameParameter("username")
        .passwordParameter("password")
        .defaultSuccessUrl("/default", true)
        .permitAll();

    // Logout
    http.logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login/form?logout").deleteCookies("JSESSIONID").invalidateHttpSession(true)
        .permitAll();

    // Anonymous
    http.anonymous();

    // CSRF is enabled by default, with Java Config
    http.csrf().disable();

    // Exception Handling
    http.exceptionHandling()
        .accessDeniedPage("/errors/403");

    // Enable <frameset> in order to use H2 web console
    http.headers().frameOptions().disable();
  }

  @Bean
  @DependsOn({"defaultCalendarService"})
  @Description("AuthenticationMnager that will generate an authentication token unlike {@link PreAuthenticatedAuthenticationProvider}")
  public CalendarUserAuthenticationProvider calendarUserAuthenticationProvider(
      CalendarService calendarService,
      PasswordEncoder passwordEncoder) {
    return new CalendarUserAuthenticationProvider(calendarService, passwordEncoder);
  }


//  @Bean
//  @Description("UserDetailsByNameServiceWrapper")
//  public UserDetailsByNameServiceWrapper authenticationUserDetailsService(final UserDetailsService calendarUserDetailsService) {
//    return new UserDetailsByNameServiceWrapper() {{
//      setUserDetailsService(calendarUserDetailsService);
//    }};
//  }


  /**
   * This is the equivalent to:
   * <pre>
   *     <http pattern="/resources/**" security="none"/>
   *     <http pattern="/css/**" security="none"/>
   *     <http pattern="/webjars/**" security="none"/>
   * </pre>
   */
  @Override
  public void configure(final WebSecurity web) throws Exception {

    // Ignore static resources and webjars from Spring Security
    web.ignoring()
        .antMatchers("/resources/**")
        .antMatchers("/css/**")
        .antMatchers("/webjars/**")
    ;

    // Thymeleaf needs to use the Thymeleaf configured FilterSecurityInterceptor
    // and not the default Filter from AutoConfiguration.
    final HttpSecurity http = getHttp();
    web.postBuildAction(() -> web.securityInterceptor(http.getSharedObject(FilterSecurityInterceptor.class)));
  }

}
