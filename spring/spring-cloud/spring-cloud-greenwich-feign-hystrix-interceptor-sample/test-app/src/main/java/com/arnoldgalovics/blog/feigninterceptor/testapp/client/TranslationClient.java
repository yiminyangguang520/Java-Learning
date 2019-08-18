package com.arnoldgalovics.blog.feigninterceptor.testapp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author litz-a
 */
@FeignClient(name = "translation", url = "http://localhost:9001")
public interface TranslationClient {

  /**
   * message
   * @return
   */
  @RequestMapping("/message")
  MessageResponse message();
}
