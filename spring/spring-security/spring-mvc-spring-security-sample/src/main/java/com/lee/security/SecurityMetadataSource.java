package com.lee.security;

import com.lee.dao.ResourceDao;
import com.lee.security.tools.AntUrlPathMatcher;
import com.lee.security.tools.UrlMatcher;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * 资源元数据类
 * 负责提供资源数据信息
 * @author litz-a
 */
public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

  private UrlMatcher urlMatcher = new AntUrlPathMatcher();

  private ResourceDao resourceDao = new ResourceDao();

  private static Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<>();

  public SecurityMetadataSource() {
    resourceMap = resourceDao.getResourceMap();
  }


  @Override
  public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
    String url = ((FilterInvocation) object).getRequestUrl();

    for (String resURL : resourceMap.keySet()) {
      if (urlMatcher.pathMatchesUrl(resURL, url)) {
        return resourceMap.get(resURL);
      }
    }
    return null;
  }

  @Override
  public Collection<ConfigAttribute> getAllConfigAttributes() {
    return null;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return true;
  }
}
