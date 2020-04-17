package com.memorynotfound.spring.security.recaptcha;

import java.net.URI;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

/**
 * @author min
 */
@Service
public class ReCaptchaService {

  private static final Logger log = LoggerFactory.getLogger(ReCaptchaService.class);

  @Autowired
  private RestOperations restTemplate;

  @Autowired
  private CaptchaSettings captchaSettings;

  @Autowired
  private HttpServletRequest request;

  public boolean validate(String reCaptchaResponse) {
    URI verifyUri = URI.create(String.format(
        captchaSettings.getUrl() + "?secret=%s&response=%s&remoteip=%s",
        captchaSettings.getSecret(),
        reCaptchaResponse,
        request.getRemoteAddr()
    ));

    try {
      ReCaptchaResponse response = restTemplate.getForObject(verifyUri, ReCaptchaResponse.class);
      return response.isSuccess();
    } catch (Exception ignored) {
      log.error("", ignored);
      // ignore when google services are not available
      // maybe add some sort of logging or trigger that'll alert the administrator
    }

    return true;
  }

}
