package com.mayi.springcloud.client;

import com.mayi.springcloud.fallback.UserFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author litz-a
 */
@FeignClient(name = "service-user", fallback = UserFallback.class)
public interface UserFeignClient {

  /**
   * listUsers
   * @return
   */
  @GetMapping("/listUsers")
  String listUsers();

}
