package com.example.webfluxsessiondemo.security;

import com.example.webfluxsessiondemo.model.User;
import com.example.webfluxsessiondemo.repo.UserRepo;
import com.example.webfluxsessiondemo.security.userdetails.UserInfo;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.security.auth.login.AccountNotFoundException;
import java.util.Map;

/**
 * @author wangxing
 * @create 2018/12/26
 */
public class CustomReactiveUserDetailsService implements ReactiveUserDetailsService {

  private final static Logger logger = LoggerFactory.getLogger(CustomReactiveUserDetailsService.class);

  private Map<String, UserInfo> userInfoMap = Maps.newConcurrentMap();

  @Resource
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserRepo userRepo;

  @Override
  public Mono<UserDetails> findByUsername(final String username) {
    if (StringUtils.isBlank(username)) {
      return Mono.error(new AccountNotFoundException());
    }
    logger.info("loadUserByUsername:{}", username);
    User user = userRepo.getByUserName(username);
    return Mono.just(new UserInfo<>(user, user.getId(), username, this.passwordEncoder.encode("123456"), Sets.newHashSet()));
  }

}
