package com.packtpub.springsecurity.configuration;

import com.packtpub.springsecurity.authentication.CalendarUserAuthenticationProvider;
import com.packtpub.springsecurity.core.userdetails.CalendarUserDetailsService;
import com.packtpub.springsecurity.service.DefaultCalendarService;
import com.packtpub.springsecurity.service.UserDetailsServiceImpl;
import com.packtpub.springsecurity.web.access.expression.CustomWebSecurityExpressionHandler;
import com.packtpub.springsecurity.web.access.intercept.FilterInvocationServiceSecurityMetadataSource;
import com.packtpub.springsecurity.web.authentication.rememberme.IpAwarePersistentTokenRepository;
import java.util.Arrays;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

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
//@EnableGlobalAuthentication
@Import({CustomAuthorizationConfig.class})
@Order(SecurityProperties.IGNORED_ORDER - 2)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

  @Autowired
  private CalendarUserAuthenticationProvider calendarUserAuthenticationProvider;

  @Autowired
  private AccessDecisionManager accessDecisionManager;

  @Autowired
  private FilterInvocationServiceSecurityMetadataSource metadataSource;

  @Autowired
  private CustomWebSecurityExpressionHandler customWebSecurityExpressionHandler;


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
   * @see {@link DefaultCalendarService}
   */
  @Override
  public void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth
        .authenticationProvider(calendarUserAuthenticationProvider);
  }

  /**
   * BCryptPasswordEncoder password encoder
   */
  @Bean
  @Description("Standard PasswordEncoder")
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(4);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
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

    // Access Decision Manager
    http.authorizeRequests()
        .anyRequest()
        .authenticated()
        .accessDecisionManager(accessDecisionManager)
        .expressionHandler(customWebSecurityExpressionHandler)
    ;

    // Matching
        /*http.authorizeRequests()
                // FIXME: TODO: Allow anyone to use H2 (NOTE: NOT FOR PRODUCTION USE EVER !!! )
                .antMatchers("/admin/h2/**").permitAll()

                .antMatchers("/").permitAll()
                .antMatchers("/login/*").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/signup/*").permitAll()
                .antMatchers("/errors/**").permitAll()
                .antMatchers("/admin/*").access("hasRole('ADMIN') and isFullyAuthenticated()")
                .antMatchers("/events/").hasRole("ADMIN")
                .antMatchers("/**").hasRole("USER");*/

    //<expression-handler ref="customWebSecurityExpressionHandler"/>

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
        .accessDeniedPage("/errors/403")
    ;

    // Enable <frameset> in order to use H2 web console
    http.headers().frameOptions().disable();
  }


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
    web.postBuildAction(() -> {
      FilterSecurityInterceptor fsi = http.getSharedObject(FilterSecurityInterceptor.class);
      fsi.setSecurityMetadataSource(metadataSource);
      web.securityInterceptor(fsi);
//       web.securityInterceptor(http.getSharedObject(FilterSecurityInterceptor.class));
    });
  }

}
