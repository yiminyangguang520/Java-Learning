package com.lee.errorhandle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author min
 * @date 2018-05-22
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class Http401Exception extends Exception {

  public Http401Exception(String s) {
    super(s);
  }
}
