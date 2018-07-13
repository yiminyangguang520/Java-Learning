package org.jdonee.cooking.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.jdonee.cooking.domain.ModuleInfo;
import org.jdonee.cooking.domain.UserInfo;
import org.jdonee.cooking.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author litz-a
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private ModuleService moduleService;

  /**
   * 根据账号Account查询当前用户
   */
  @Override
  public UserInfo findByAccount(String account) {
    return userMapper.findByAccount(account);
  }

  /**
   * 获取资源集合
   */
  @Override
  public Set<String> findPermissions(String account) {
    UserInfo user = findByAccount(account);
    List<ModuleInfo> modules = moduleService.findModuleByUserId(user.getId());
    return modules.stream()
        .map(ModuleInfo::getModuleKey)
        .collect(Collectors.toSet());
  }

  /**
   * 获取URL权限
   */
  @Override
  public List<String> findPermissionUrl(String account) {
    UserInfo user = findByAccount(account);
    List<ModuleInfo> modules = moduleService.findModuleByUserId(user.getId());
    return modules.stream()
        .filter(moduleInfo -> moduleInfo.getModuleType() == ModuleInfo.URL_TYPE)
        .map(ModuleInfo::getModulePath)
        .collect(Collectors.toList());
  }
}