package com.javasampleapproach.springexceptionhandler.controller;

import com.javasampleapproach.springexceptionhandler.customexception.CustomGeneralException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author litz-a
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CustomGeneralException.class)
  public ModelAndView handleCustomGeneralException() {
    ModelAndView model = new ModelAndView();
    // add needed model attributes
    model.setViewName("generalexception");
    return model;
  }

}