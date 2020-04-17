package org.jdonee.cooking.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author min
 */
@Controller
public class IndexController {

  /**
   * Go Index
   */
  @RequestMapping(value = {"", "/", "index"})
  public String index() {
    return "index";
  }

  /**
   * unauthor
   */
  @RequestMapping("unauthor")
  public String unauthor() {
    return "unauthor";
  }

  /**
   * reports
   */
  @RequestMapping("reports")
  public String reports() {
    return "reports";
  }
}
