package com.memorynotfound.spring.security.recaptcha;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author min
 */
@Component
@ConfigurationProperties(prefix = "google.recaptcha")
public class CaptchaSettings {

  private String url;
  private String key;
  private String secret;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }
}
