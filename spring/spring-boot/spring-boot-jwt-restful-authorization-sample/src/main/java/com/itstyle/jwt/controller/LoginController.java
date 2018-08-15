package com.itstyle.jwt.controller;

import com.itstyle.jwt.constant.Domain;
import com.itstyle.jwt.model.User;
import com.itstyle.jwt.service.TokenManager;
import com.itstyle.jwt.service.UserService;
import com.itstyle.jwt.dto.ResponseModel;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@RestController
public class LoginController {

  @Autowired
  private UserService userService;

  @Autowired
  private TokenManager tokenManager;

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public ResponseEntity login(String username, String password, HttpServletResponse response) throws Exception {
    User user = userService.findByUserName(username);
    if (user != null) {
      String token =  tokenManager.getToken(user);
      if (StringUtils.isNotEmpty(token)) {
        return new ResponseEntity<>(ResponseModel.ok(token), HttpStatus.OK);
      }
    }

    String result = userService.domainAccountAuthenticate(username, password);
    if (!Domain.PASS.equals(result)) {
      return new ResponseEntity<>(ResponseModel.error(), HttpStatus.BAD_REQUEST);
    }

    user = userService.getOrCreateUser(username);
    if (user != null) {
      //把token返回给客户端-->客户端保存至cookie-->客户端每次请求附带cookie参数
      String JWT = tokenManager.createToken(user);
      return new ResponseEntity<>(ResponseModel.ok(JWT), HttpStatus.OK);
    }

    return new ResponseEntity<>(ResponseModel.error(), HttpStatus.BAD_REQUEST);
  }

  @RequestMapping(value = "/user/info", method = RequestMethod.GET)
  public ResponseEntity description(String username) {
    User user = userService.findByUserName(username);
    return new ResponseEntity<>(ResponseModel.ok(user), HttpStatus.OK);
  }

  @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
  public ResponseEntity logout(String username) {
    User user = userService.findByUserName(username);
    tokenManager.deleteToken(user.getId());
    return new ResponseEntity<>(ResponseModel.ok(), HttpStatus.OK);
  }
}
