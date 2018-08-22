package com.lee.errorhandle.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author litz-a
 * @date 2018-05-21
 */
@Controller
@Profile(value = {"myErrorController"})
public class ExceptionHandleController extends AbstractErrorController {


  public ExceptionHandleController(ErrorAttributes errorAttributes) {
    super(errorAttributes);
  }

  @RequestMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Map<String, Object> handleError(HttpServletRequest request) {
    Map<String, Object> errorAttributes = super.getErrorAttributes(request, true);
    return errorAttributes;
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }

}
