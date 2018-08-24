package com.us.example.service;

import com.us.example.dao.PermissionMapper;
import com.us.example.model.Permission;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

/**
 * @author litz-a
 */
@Service
public class CustomInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

  @Autowired
  private PermissionMapper permissionDao;

  private Map<String, Collection<ConfigAttribute>> map = new HashMap<>();

  /**
   * 加载权限表中所有权限
   */
  public void loadResourceDefine() {
    List<Permission> permissions = permissionDao.findAll();

    // 用权限的getUrl() 作为map的key;
    // 用ConfigAttribute的集合作为value,此处只添加了用户的名字，其实还可以添加更多权限的信息
    // 例如请求方法到ConfigAttribute的集合中去。此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数
    map = permissions.stream()
        .collect(Collectors.toMap(p -> p.getUrl(), p -> Collections.singletonList(new SecurityConfig(p.getName()))));
  }

  /**
   * 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行
   * @param object
   * @return
   * @throws IllegalArgumentException
   */
  @Override
  public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
    if (map.size() == 0) {
      loadResourceDefine();
    }

    HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
    return map.entrySet().stream()
        .filter(item -> new AntPathRequestMatcher(item.getKey()).matches(request))
        .map(Map.Entry::getValue)
        .findFirst()
        .orElse(Collections.EMPTY_LIST);
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
