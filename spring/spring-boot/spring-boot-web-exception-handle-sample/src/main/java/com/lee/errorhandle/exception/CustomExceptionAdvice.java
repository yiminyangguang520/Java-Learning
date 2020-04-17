package com.lee.errorhandle.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author min
 * @date 2018-05-22
 */
@ControllerAdvice
public class CustomExceptionAdvice {

  @ExceptionHandler(IllegalAccessError.class)
  @ResponseBody
  ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
    HttpStatus status = getStatus(request);
    ResponseEntity responseEntity = new ResponseEntity(ex, status);
    return responseEntity;
  }

  private HttpStatus getStatus(HttpServletRequest request) {
    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    if (statusCode == null) {
      return HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return HttpStatus.valueOf(statusCode);
  }
}
