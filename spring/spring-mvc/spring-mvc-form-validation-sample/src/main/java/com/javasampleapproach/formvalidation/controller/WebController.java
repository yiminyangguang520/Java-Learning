package com.javasampleapproach.formvalidation.controller;

import com.javasampleapproach.formvalidation.model.RequestInfo;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author min
 */
@Controller
public class WebController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String requestInfo(RequestInfo requestInfo) {
    return "request";
  }

  @RequestMapping(value = "/", method = RequestMethod.POST)
  String requestInfo(@Valid @ModelAttribute("requestInfo") RequestInfo requestInfo, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "request";
    }

    System.out.println(requestInfo.toString());

    return "successful";
  }
}
