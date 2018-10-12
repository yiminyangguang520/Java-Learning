package com.packtpub.springsecurity.web.access.intercept;

import com.packtpub.springsecurity.web.access.expression.CustomWebSecurityExpressionHandler;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.ExpressionBasedFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

/**
 * FilterInvocationServiceSecurityMetadataSource Requires {@link FilterInvocation}
 * @author litz-a
 */
@Component("filterInvocationServiceSecurityMetadataSource")
public class FilterInvocationServiceSecurityMetadataSource
    implements FilterInvocationSecurityMetadataSource, InitializingBean {

  private FilterInvocationSecurityMetadataSource delegate;
  private RequestConfigMappingService requestConfigMappingService;
  private SecurityExpressionHandler<FilterInvocation> expressionHandler;

  @Autowired
  public FilterInvocationServiceSecurityMetadataSource(CustomWebSecurityExpressionHandler expressionHandler,
      RequestConfigMappingService filterInvocationService) {
    this.expressionHandler = expressionHandler;
    this.requestConfigMappingService = filterInvocationService;
  }

  @Override
  public Collection<ConfigAttribute> getAllConfigAttributes() {
    return this.delegate.getAllConfigAttributes();
  }

  @Override
  public Collection<ConfigAttribute> getAttributes(Object object) {
    return this.delegate.getAttributes(object);
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return this.delegate.supports(clazz);
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    List<RequestConfigMapping> requestConfigMappings = requestConfigMappingService.getRequestConfigMappings();
    LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>(requestConfigMappings.size());
    for (RequestConfigMapping requestConfigMapping : requestConfigMappings) {
      RequestMatcher matcher = requestConfigMapping.getMatcher();
      requestMap.put(matcher, requestConfigMapping.getAttributes());
    }
    this.delegate = new ExpressionBasedFilterInvocationSecurityMetadataSource(requestMap, expressionHandler);
  }
}
