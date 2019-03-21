package com.mayi.springcloud.fallback;

import com.mayi.springcloud.client.UserFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author litz-a
 */
@Component
public class UserFallback implements UserFeignClient {

  @Override
  public String listUsers() {
    // TODO Auto-generated method stub
    return "服务调用失败";
  }

}
