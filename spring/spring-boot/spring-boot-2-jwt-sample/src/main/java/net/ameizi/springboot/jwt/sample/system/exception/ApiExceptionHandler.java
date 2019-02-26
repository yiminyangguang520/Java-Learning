package net.ameizi.springboot.jwt.sample.system.exception;

import lombok.extern.slf4j.Slf4j;
import net.ameizi.springboot.jwt.sample.business.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

  @ExceptionHandler(AuthorizationException.class)
  @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
  @ResponseBody
  public ResponseEntity handleAuthorizationExceptionExceptionError(RuntimeException ex) {
    ex.printStackTrace();
    Message message = new Message();
    message.setCode(String.valueOf(HttpStatus.UNAUTHORIZED.value()));
    message.setMessage(ex.getMessage());
    return new ResponseEntity(message, HttpStatus.UNAUTHORIZED);
  }

}
