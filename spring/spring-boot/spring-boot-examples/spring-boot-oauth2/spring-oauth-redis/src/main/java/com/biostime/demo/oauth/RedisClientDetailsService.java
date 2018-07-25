package com.biostime.demo.oauth;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

/**
 * Function:Redis授权
 * <P> 版权所有 ©2013 Biostime Inc.. All Rights Reserved
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * User: 12360 Date: 2016/8/16 Time: 16:16
 *
 * @author litz-a
 */
@Data
@Service
@ConfigurationProperties(prefix = "oauth.details")
public class RedisClientDetailsService implements ClientDetailsService {

  private String clientId;
  private String clientSecret;
  private String backClientId;
  private String backClientSecret;
  private String resourceIds;
  private String scopes;
  private String grantTypes;
  private String authorities;
  private Integer accessTokenValiditySeconds;
  private Integer backTokenValiditySeconds;
  private Map<String, ClientDetails> clientDetailsStore = new HashMap();

  public RedisClientDetailsService() {

  }

  @Override
  public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
    if (clientDetailsStore.keySet().isEmpty()) {
      init();
    }
    ClientDetails details = clientDetailsStore.get(clientId);
    return details;
  }

  public void init() {
    BaseClientDetails details = new BaseClientDetails(clientId, resourceIds, scopes, grantTypes, authorities);
    details.setClientSecret(clientSecret);
    details.setAccessTokenValiditySeconds(accessTokenValiditySeconds);
    clientDetailsStore.put(clientId, details);

    BaseClientDetails backDetails = new BaseClientDetails(backClientId, resourceIds, scopes, grantTypes, authorities);
    backDetails.setClientSecret(backClientSecret);
    backDetails.setAccessTokenValiditySeconds(backTokenValiditySeconds);
    clientDetailsStore.put(backClientId, backDetails);
  }
}

