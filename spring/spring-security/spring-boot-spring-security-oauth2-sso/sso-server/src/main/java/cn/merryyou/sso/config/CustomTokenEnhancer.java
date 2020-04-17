package cn.merryyou.sso.config;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import java.util.Collections;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

/**
 * @author min
 */
public class CustomTokenEnhancer implements TokenEnhancer {

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(Collections.singletonMap("organization", authentication.getName() + randomAlphabetic(4)));
    return accessToken;
  }
}