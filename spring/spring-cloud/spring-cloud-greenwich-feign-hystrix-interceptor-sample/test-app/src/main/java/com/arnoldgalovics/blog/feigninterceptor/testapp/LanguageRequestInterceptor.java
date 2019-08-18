package com.arnoldgalovics.blog.feigninterceptor.testapp;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.util.Objects;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author litz-a
 */
@Component
public class LanguageRequestInterceptor implements RequestInterceptor {

  private static final String ACCEPT_LANGUAGE_HEADER = "Accept-Language";

  @Override
  public void apply(RequestTemplate requestTemplate) {
    ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (Objects.isNull(requestAttributes)) {
      return;
    }

    HttpServletRequest request = requestAttributes.getRequest();
    if (Objects.isNull(request)) {
      return;
    }

    String language = request.getHeader(ACCEPT_LANGUAGE_HEADER);
    if (StringUtils.isEmpty(language)) {
      return;
    }

    requestTemplate.header(ACCEPT_LANGUAGE_HEADER, language);
  }
}
