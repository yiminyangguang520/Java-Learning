package demo.config;

import com.allanditzel.springframework.security.web.csrf.CsrfTokenResponseHeaderBindingFilter;
import demo.controller.BaseController;
import demo.controller.SessionController;
import demo.security.CustomAuthenticationFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

/**
 * @author min
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserDetailsService userDetailsServiceImpl;

  @Autowired
  private CustomLoginHandler customLoginHandler;

  @Autowired
  private CustomLogoutHandler customLogoutHandler;

  @Autowired
  private CustomAccessDeniedHandler customAccessDeniedHandler;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(userDetailsServiceImpl)
        .passwordEncoder(passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/api/admin/**").hasRole("ADMIN")
        .antMatchers("/api/basic/**").hasRole("BASIC")
        .antMatchers("/api/session").permitAll()
        .antMatchers(HttpMethod.GET).permitAll()
        .antMatchers("/api/**").hasRole("BASIC");

    http.formLogin();

    http.logout()
        .logoutUrl("/api/session/logout")
        .addLogoutHandler(customLogoutHandler)
        .logoutSuccessHandler(customLogoutHandler);

    http.exceptionHandling()
        .accessDeniedHandler(customAccessDeniedHandler)
        .authenticationEntryPoint(customAccessDeniedHandler);

    http.csrf()
        .ignoringAntMatchers("/api/session/**");

    http.addFilterBefore(new AcceptHeaderLocaleFilter(), UsernamePasswordAuthenticationFilter.class);

    http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    http.addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(), CsrfFilter.class);
  }

  private CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
    CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
    filter.setAuthenticationSuccessHandler(customLoginHandler);
    filter.setAuthenticationFailureHandler(customLoginHandler);
    filter.setAuthenticationManager(authenticationManager());
    filter.setFilterProcessesUrl("/api/session/login");
    return filter;
  }

  private static void responseText(HttpServletResponse response, String content) throws IOException {
    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
    response.setContentLength(bytes.length);
    response.getOutputStream().write(bytes);
    response.flushBuffer();
  }

  @Component
  public static class CustomAccessDeniedHandler extends BaseController implements AuthenticationEntryPoint, AccessDeniedHandler {

    /**
     * NoLogged Access Denied
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
      responseText(response, errorMessage(authException.getMessage()));
    }

    /**
     * Logged Access Denied
     * @param request
     * @param response
     * @param accessDeniedException
     * @throws IOException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
      responseText(response, errorMessage(accessDeniedException.getMessage()));
    }
  }

  @Component
  public static class CustomLoginHandler extends BaseController implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    /**
     * Login Success
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
      LOGGER.info("User login successfully, name={}", authentication.getName());
      responseText(response, objectResult(SessionController.getJSON(authentication)));
    }

    /**
     * Login Failure
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
      responseText(response, errorMessage(exception.getMessage()));
    }
  }

  @Component
  public static class CustomLogoutHandler extends BaseController implements LogoutHandler, LogoutSuccessHandler {

    /**
     * Before Logout
     * @param request
     * @param response
     * @param authentication
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

    }

    /**
     * After Logout
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
      responseText(response, objectResult(SessionController.getJSON(null)));
    }
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  private static class AcceptHeaderLocaleFilter implements Filter {

    private AcceptHeaderLocaleResolver localeResolver;

    private AcceptHeaderLocaleFilter() {
      localeResolver = new AcceptHeaderLocaleResolver();
      localeResolver.setDefaultLocale(Locale.US);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      Locale locale = localeResolver.resolveLocale((HttpServletRequest) request);
      LocaleContextHolder.setLocale(locale);

      chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
  }
}
