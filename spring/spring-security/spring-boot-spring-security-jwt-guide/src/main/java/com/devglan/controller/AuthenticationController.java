package com.devglan.controller;

import com.devglan.config.TokenProvider;
import com.devglan.model.AuthToken;
import com.devglan.model.LoginUser;
import com.devglan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenProvider jwtTokenUtil;

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
  public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

    final Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginUser.getUsername(),
            loginUser.getPassword()
        )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);
    final String token = jwtTokenUtil.generateToken(authentication);
    return ResponseEntity.ok(new AuthToken(token));
  }

}
