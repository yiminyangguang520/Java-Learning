package com.lee.dao;

import com.lee.common.Constant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * 用户信息访问类
 *
 * 可以在此处使用需要的方式获取信息，如从数据库、XML、文本等获取
 * @author min
 */
public class UserDao {

  private static Map<String, User> userMap = loadUser();

  private synchronized static Map<String, User> loadUser() {
    Map<String, User> userInfoMap = new HashMap<>(3);
    final GrantedAuthority gaTeacher = new SimpleGrantedAuthority(Constant.ROLE_TEACHER);
    final GrantedAuthority gaStudent = new SimpleGrantedAuthority(Constant.ROLE_STUDENT);
    final GrantedAuthority gaNotice = new SimpleGrantedAuthority(Constant.ROLE_NOTICE);

    userInfoMap.put(
        "teacher",
        new User("teacher", "teacher", true, true, true, true, new ArrayList<GrantedAuthority>() {{
          add(gaTeacher);
          add(gaNotice);
        }})
    );

    userInfoMap.put(
        "student",
        new User("student", "student", true, true, true, true, new ArrayList<GrantedAuthority>() {{
          add(gaStudent);
        }})
    );

    userInfoMap.put(
        "notice",
        new User("notice", "notice", true, true, true, true, new ArrayList<GrantedAuthority>() {{
          add(gaNotice);
        }})
    );
    return userInfoMap;
  }

  public User getUser(String username) {
    return userMap.get(username);
  }
}
