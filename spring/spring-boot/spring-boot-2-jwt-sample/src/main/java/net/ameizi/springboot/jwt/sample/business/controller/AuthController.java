package net.ameizi.springboot.jwt.sample.business.controller;

import io.jsonwebtoken.Claims;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import net.ameizi.springboot.jwt.sample.business.model.AccessToken;
import net.ameizi.springboot.jwt.sample.business.model.Message;
import net.ameizi.springboot.jwt.sample.business.model.User;
import net.ameizi.springboot.jwt.sample.business.repository.UserRepository;
import net.ameizi.springboot.jwt.sample.system.exception.AuthorizationException;
import net.ameizi.springboot.jwt.sample.system.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1.0")
public class AuthController {

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private UserRepository userRepository;

  @PostMapping(value = "/register")
  public ResponseEntity addUser(@RequestBody User user) {
    if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getLoginName()) || StringUtils.isEmpty(user.getPassword())) {
      return new ResponseEntity<>(new Message("资源不完整"), HttpStatus.BAD_REQUEST);
    }
    if (userRepository.findByLoginName(user.getLoginName()) != null) {
      return new ResponseEntity<>(new Message("用户已经存在"), HttpStatus.IM_USED);
    }
    user.setRoles("user");
    user.setCreateAt(new Date());
    user.setUpdateAt(new Date());
    userRepository.save(user);
    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }


  @PostMapping(value = {"/auth/token", "/auth/refresh-token"})
  public ResponseEntity getToken(@RequestBody User user, Device device) {
    if (StringUtils.isEmpty(user.getLoginName()) || StringUtils.isEmpty(user.getPassword())) {
      throw new AuthorizationException("认证失败");
    }
    User userDetail = userRepository.findByLoginNameAndPassword(user.getLoginName(), user.getPassword());
    if (userDetail == null) {
      return new ResponseEntity<>(new Message("认证失败"), HttpStatus.UNAUTHORIZED);
    } else {
      AccessToken accessToken = new AccessToken();
      accessToken.setAccess_token(jwtUtil.generateToken(Integer.toString(userDetail.getId()), userDetail.getLoginName(), userDetail.getRoles(), device));
      accessToken.setToken_type("Bearer");
      accessToken.setExpires_in(jwtUtil.getTtl());
      return new ResponseEntity<>(accessToken, HttpStatus.OK);
    }
  }


  @GetMapping(value = "/users/current")
  public ResponseEntity getUser(final HttpServletRequest request) {
    final Claims claims = (Claims) request.getAttribute("claims");
    String userIdStr = claims.getId();
    if (StringUtils.isEmpty(userIdStr)) {
      return new ResponseEntity<>(new Message("认证失败"), HttpStatus.UNAUTHORIZED);
    }
    Integer userId = Integer.parseInt(userIdStr);
    User user = userRepository.findById(userId).orElse(null);
    if (user == null) {
      return new ResponseEntity<>(new Message("资源不存在"), HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

}
