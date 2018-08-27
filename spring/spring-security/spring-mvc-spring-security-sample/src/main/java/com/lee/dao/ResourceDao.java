package com.lee.dao;

import com.lee.common.Constant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

/**
 * 角色-资源数据访问类
 * 可以在此处使用需要的方式获取信息，如从数据库、XML、文本等获取
 * @author litz-a
 */
public class ResourceDao {

  private static Map<String, Collection<ConfigAttribute>> resourceMap = loadResourceMap();

  private synchronized static Map<String, Collection<ConfigAttribute>> loadResourceMap() {
    Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<>(5);

    final SecurityConfig scTeacher = new SecurityConfig(Constant.ROLE_TEACHER);
    final SecurityConfig scStudent = new SecurityConfig(Constant.ROLE_STUDENT);
    final SecurityConfig scNotice = new SecurityConfig(Constant.ROLE_NOTICE);

    resourceMap.put("/student.html", new ArrayList<ConfigAttribute>() {{
      add(scStudent);
    }});

    resourceMap.put("/student/**", new ArrayList<ConfigAttribute>() {{
      add(scStudent);
    }});

    resourceMap.put("/teacher.html", new ArrayList<ConfigAttribute>() {{
      add(scTeacher);
    }});

    resourceMap.put("/class.html", new ArrayList<ConfigAttribute>() {{
      add(scStudent);
      add(scTeacher);
    }});

    resourceMap.put("/notice.html", new ArrayList<ConfigAttribute>() {{
      add(scTeacher);
      add(scNotice);
    }});

    return resourceMap;
  }

  public Map<String, Collection<ConfigAttribute>> getResourceMap() {
    return resourceMap;
  }
}
