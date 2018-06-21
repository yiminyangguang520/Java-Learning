package com.lmonkiewicz.example.controller;

import com.lmonkiewicz.example.model.Address;
import com.lmonkiewicz.example.model.ErrorResponse;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lmonkiewicz
 * @date 17.03.2017
 */
@RestController
@RequestMapping("/addresses")
public class AddressController {

  @PostMapping
  public ResponseEntity<?> createAddress(@Valid @RequestBody Address message) {
    return ResponseEntity.created(URI.create("/addresses/1")).body("1");
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleException(MethodArgumentNotValidException exception) {

    String errorMsg = exception.getBindingResult().getFieldErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .findFirst()
        .orElse(exception.getMessage());

    return ErrorResponse.builder().message(errorMsg).build();
  }


}
