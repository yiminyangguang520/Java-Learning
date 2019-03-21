package com.mayi.springcloud.api;

import com.mayi.springcloud.client.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@RestController
public class UserFeignApi {

  @Autowired
  private UserFeignClient userFeignClient;

  @GetMapping("/listUsersByFeign")
  public String ListUsers() {
    String users = this.userFeignClient.listUsers();
    return users;
  }

}
