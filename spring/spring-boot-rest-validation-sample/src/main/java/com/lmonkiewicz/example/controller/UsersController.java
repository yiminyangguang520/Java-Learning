package com.lmonkiewicz.example.controller;

import com.lmonkiewicz.example.model.ErrorResponse;
import com.lmonkiewicz.example.model.User;
import java.net.URI;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/users")
public class UsersController {

  @PostMapping
  public ResponseEntity<?> createUser(@Validated(User.New.class) @RequestBody User user) {
    return ResponseEntity.created(URI.create("/users/1")).body("1");
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getUser(@PathVariable Long id) {
    if (id == 1L) {
      final User user = User.builder().id(1L).firstName("Stefan").lastName("Stefanowsky").age(32).build();
      return ResponseEntity.ok(user);
    } else {
      return ResponseEntity.notFound().build();
    }
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
