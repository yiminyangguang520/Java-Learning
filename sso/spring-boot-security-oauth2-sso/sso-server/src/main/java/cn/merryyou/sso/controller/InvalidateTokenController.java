package cn.merryyou.sso.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@Slf4j
@RestController
public class InvalidateTokenController {

  @Autowired
  private ConsumerTokenServices consumerTokenServices;

  @PostMapping(value = "/invalidateToken")
  public Map<String, String> logout(@RequestParam(name = "access_token") String accessToken) {
    log.debug("Invalidating token {}", accessToken);
    consumerTokenServices.revokeToken(accessToken);
    return Collections.singletonMap("access_token", accessToken);
  }
}