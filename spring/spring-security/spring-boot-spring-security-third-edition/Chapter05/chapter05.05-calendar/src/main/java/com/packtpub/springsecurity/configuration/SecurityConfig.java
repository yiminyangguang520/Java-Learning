package com.packtpub.springsecurity.configuration;

import com.packtpub.springsecurity.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * Spring Security Config Class
 *
 * @author min
 * @see {@link WebSecurityConfigurerAdapter}
 * @since chapter05.00
 */
@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

  @Autowired
  private UserDetailsService userDetailsService;


  /**
   * Configure AuthenticationManager with inMemory credentials.
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
    auth
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder())
    ;
  }

  /**
   * The parent method from {@link WebSecurityConfigurerAdapter} (public UserDetailsService userDetailsService()) originally returns a {@link UserDetailsService}, but
   * this needs to be a {@link UserDetailsManager} UserDetailsManager vs UserDetailsService
   */
  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    return new UserDetailsServiceImpl();
  }

  /**
   * Standard SHA-256 Password Encoder without a SALT
   * @return StandardPasswordEncoder
   *
   * @see StandardPasswordEncoder
   */
//    @Bean
//    public PasswordEncoder standardPasswordEncoder(){
//        return new StandardPasswordEncoder();
//    }

  /**
   * BCryptPasswordEncoder password encoder
   */
  @Bean
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
    http.authorizeRequests()
        // FIXME: TODO: Allow anyone to use H2 (NOTE: NOT FOR PRODUCTION USE EVER !!! )
        .antMatchers("/admin/h2/**").permitAll()

        .antMatchers("/").permitAll()
        .antMatchers("/login/*").permitAll()
        .antMatchers("/logout").permitAll()
        .antMatchers("/signup/*").permitAll()
        .antMatchers("/errors/**").permitAll()
        .antMatchers("/admin/*").hasRole("ADMIN")
        .antMatchers("/events/").hasRole("ADMIN")
        .antMatchers("/**").hasRole("USER")

        .and().exceptionHandling().accessDeniedPage("/errors/403")

        .and().formLogin()
        .loginPage("/login/form")
        .loginProcessingUrl("/login")
        .failureUrl("/login/form?error")
        .usernameParameter("username")
        .passwordParameter("password")
        .defaultSuccessUrl("/default", true)
        .permitAll()

        .and().logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login/form?logout")
        .permitAll()

        .and().anonymous()

        // CSRF is enabled by default, with Java Config
        .and().csrf().disable();

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
    web.ignoring()
        .antMatchers("/resources/**")
        .antMatchers("/css/**")
        .antMatchers("/webjars/**")
    ;
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean()
      throws Exception {
    return super.authenticationManagerBean();
  }

} // The End...
