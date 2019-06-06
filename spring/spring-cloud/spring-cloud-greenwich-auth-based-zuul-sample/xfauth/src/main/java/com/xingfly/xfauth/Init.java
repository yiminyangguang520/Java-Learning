package com.xingfly.xfauth;

import com.google.common.collect.Sets;
import com.xingfly.xfauth.domain.Authority;
import com.xingfly.xfauth.domain.Role;
import com.xingfly.xfauth.domain.User;
import com.xingfly.xfauth.repository.AuthorityRepository;
import com.xingfly.xfauth.repository.RoleRepository;
import com.xingfly.xfauth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by SuperS on 2017/9/25.
 *
 * @author SuperS
 */
@Component
@AllArgsConstructor
public class Init implements CommandLineRunner {

  private UserService userService;
  private AuthorityRepository authorityRepository;
  private RoleRepository roleRepository;

  @Override
  public void run(String... strings) throws Exception {

    //权限
    Authority authority = new Authority();
    authority.setName("查询");
    authority.setValue("query");
    authorityRepository.save(authority);

    //角色
    Role admin = new Role();
    admin.setName("管理员");
    admin.setValue("ROLE_ADMIN");
    admin.setAuthorities(Sets.newHashSet(authorityRepository.findById(1L).get()));
    roleRepository.save(admin);

    Role role = new Role();
    role.setName("普通用户");
    role.setValue("ROLE_USER");
    roleRepository.save(role);

    //用户
    User fpf = new User();
    fpf.setUsername("fpf");
    fpf.setPassword("fpf");
    fpf.setRoles(Sets.newHashSet(roleRepository.findById(2L).get()));
    userService.createUser(fpf);

    User wl = new User();
    wl.setUsername("wl");
    wl.setPassword("wl");
    wl.setRoles(Sets.newHashSet(roleRepository.findById(2L).get()));
    userService.createUser(wl);


  }
}
