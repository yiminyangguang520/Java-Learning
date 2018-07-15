package com.javasampleapproach.springexceptionhandler.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author litz-a
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not Found Exception!")
public class CustomExceptionWithHttpStatusCode extends RuntimeException {

}