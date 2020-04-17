package com.brahalla.cerberus.controller.rest;

import com.brahalla.cerberus.model.json.request.AuthenticationRequest;
import com.brahalla.cerberus.model.json.response.AuthenticationResponse;
import com.brahalla.cerberus.model.security.CerberusUser;
import com.brahalla.cerberus.security.TokenUtils;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
@RequestMapping("${cerberus.route.authentication}")
public class AuthenticationController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Value("${cerberus.token.header}")
  private String tokenHeader;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenUtils tokenUtils;

  @Autowired
  @Qualifier("userDetailsServiceImpl")
  private UserDetailsService userDetailsService;

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<?> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {

    // Perform the authentication
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        authenticationRequest.getUsername(),
        authenticationRequest.getPassword()
      )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Reload password post-authentication so we can generate token
    UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    String token = tokenUtils.generateToken(userDetails, device);

    // Return the token
    return ResponseEntity.ok(new AuthenticationResponse(token));
  }

  @RequestMapping(value = "${cerberus.route.authentication.refresh}", method = RequestMethod.GET)
  public ResponseEntity<?> authenticationRequest(HttpServletRequest request) {
    String token = request.getHeader(tokenHeader);
    String username = tokenUtils.getUsernameFromToken(token);
    CerberusUser user = (CerberusUser) userDetailsService.loadUserByUsername(username);
    if (tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
      String refreshedToken = tokenUtils.refreshToken(token);
      return ResponseEntity.ok(new AuthenticationResponse(refreshedToken));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }

}
