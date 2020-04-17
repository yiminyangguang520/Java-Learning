package cn.msgcode.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

  @Value("${text.hello}")
  private String text;

  @RequestMapping("/get")
  public String get() {
    return text;
  }
}