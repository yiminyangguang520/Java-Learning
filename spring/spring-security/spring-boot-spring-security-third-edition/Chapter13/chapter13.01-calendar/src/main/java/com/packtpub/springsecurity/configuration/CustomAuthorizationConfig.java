package com.packtpub.springsecurity.configuration;

import com.packtpub.springsecurity.access.CalendarPermissionEvaluator;
import com.packtpub.springsecurity.authentication.CalendarUserAuthenticationProvider;
import com.packtpub.springsecurity.dataaccess.EventDao;
import com.packtpub.springsecurity.service.CalendarService;
import com.packtpub.springsecurity.web.access.expression.CustomWebSecurityExpressionHandler;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Description;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.ConsensusBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;

/**
 * @author min
 */
@Configuration
public class CustomAuthorizationConfig {

  @Bean
  @Description("DefaultMethodSecurityExpressionHandler")
  public DefaultMethodSecurityExpressionHandler defaultExpressionHandler(EventDao eventDao) {
    DefaultMethodSecurityExpressionHandler deh = new DefaultMethodSecurityExpressionHandler();
    deh.setPermissionEvaluator(new CalendarPermissionEvaluator(eventDao));
    return deh;
  }

  @Bean
  @Description("AccessDecisionManager for Authorization voting")
  public AccessDecisionManager accessDecisionManager(CustomWebSecurityExpressionHandler customWebSecurityExpressionHandler) {
    List<AccessDecisionVoter<? extends Object>> decisionVoters
        = Arrays.asList(
        new WebExpressionVoter() {{
          setExpressionHandler(customWebSecurityExpressionHandler);
        }}
    );
    return new ConsensusBased(decisionVoters);
  }

//  @Bean
//  public AccessDecisionManager accessDecisionManager() {
//    List<AccessDecisionVoter<? extends Object>> decisionVoters
//        = Arrays.asList(
//        new AuthenticatedVoter(),
//        new RoleVoter(),
//        new WebExpressionVoter()
//    );
//    return new UnanimousBased(decisionVoters);
//  }

//    <bean id="unanimousBased"class="org.springframework.security.access.vote.UnanimousBased">
//        <property name="decisionVoters">
//            <list>
//                <ref bean="roleVoter"/>
//                <ref bean="authenticatedVoter"/>
//            </list>
//        </property>
//    </bean>
//    <bean id="roleVoter"class="org.springframework.security.access.vote.RoleVoter"/>
//    <bean id="authenticatedVoter"class="org.springframework.security.access.vote.AuthenticatedVoter"/>


  @Bean
  @DependsOn({"defaultCalendarService"})
  @Description("AuthenticationMnager that will generate an authentication token unlike {PreAuthenticatedAuthenticationProvider}")
  public CalendarUserAuthenticationProvider calendarUserAuthenticationProvider(
      CalendarService calendarService,
      PasswordEncoder passwordEncoder) {
    return new CalendarUserAuthenticationProvider(calendarService, passwordEncoder);
  }

}
