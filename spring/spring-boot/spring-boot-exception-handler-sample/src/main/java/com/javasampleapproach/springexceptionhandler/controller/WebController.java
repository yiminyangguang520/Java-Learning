package com.javasampleapproach.springexceptionhandler.controller;

import com.javasampleapproach.springexceptionhandler.customexception.CustomConflictException;
import com.javasampleapproach.springexceptionhandler.customexception.CustomException;
import com.javasampleapproach.springexceptionhandler.customexception.CustomExceptionWithHttpStatusCode;
import com.javasampleapproach.springexceptionhandler.customexception.CustomGeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author min
 */
@Controller
public class WebController {

  @RequestMapping(value = "/normalexception")
  public void throwException() {
    throw new RuntimeException();
  }

  @RequestMapping(value = "/customexception")
  public void throwCustomException() {
    throw new CustomException();
  }

  @RequestMapping(value = "/conflictexception")
  public void throwCustomConfictException() {
    throw new CustomConflictException();
  }

  @RequestMapping(value = "/customexceptionwithhttpstatuscode")
  public void throwCustomExceptionWithHttpStatusCode() {
    throw new CustomExceptionWithHttpStatusCode();
  }

  /*
   * Use ExceptionHadler in AdviceWebController defined
   * with @ControllerAdvice.
   */
  @RequestMapping(value = "/customgeneralexception")
  public void throwCustomGeneralException() {
    throw new CustomGeneralException();
  }

  // Convert a predefined exception to an HTTP Status code
  @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Having conflict")
  @ExceptionHandler(CustomConflictException.class)
  public void conflict() {
    // log something
  }

  @ExceptionHandler(CustomException.class)
  public String runtimeException() {
    return "exception";
  }
}
