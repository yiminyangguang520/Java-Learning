package com.example.webfluxsessiondemo.security.userdetails;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author wangxing
 * @create 2018/10/15
 */
public interface UserIdUserDetails<ID> extends UserDetails {

  ID getUserId();

}
