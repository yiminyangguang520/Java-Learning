package com.mayi.springcloud.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author litz-a
 */
@FeignClient(name = "service-user")
public interface UserFeignClient {

  /**
   * listUsers
   * @return
   */
  @GetMapping("/listUsers")
  String listUsers();

}
