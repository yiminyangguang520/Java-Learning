package com.luoshupeng.multidatasource.controller.user;

import com.luoshupeng.multidatasource.primary.entity.User;
import com.luoshupeng.multidatasource.primary.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 * Created by luoshupeng on 2018-03-20 10:35
 */
@RestController
@RequestMapping("/user/*")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("list")
  public ResponseEntity<List<User>> list() {
    return new ResponseEntity<>(userService.list(), HttpStatus.OK);
  }
}
