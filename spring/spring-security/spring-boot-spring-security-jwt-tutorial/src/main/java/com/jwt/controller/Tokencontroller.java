package com.jwt.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.config.Iconstants;
import com.jwt.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author litz-a
 */
@RestController
public class Tokencontroller {

  @PostMapping("/token")
  public ResponseEntity<String> getToken(@RequestBody User login) throws ServletException {

    String jwttoken;

    if (login.getUsername().isEmpty() || login.getPassword().isEmpty()) {
      return new ResponseEntity<>("Username or password cannot be empty.", HttpStatus.BAD_REQUEST);
    }

    String name = login.getUsername();
    String password = login.getPassword();

    if (!(name.equalsIgnoreCase("Test") && password.equalsIgnoreCase("1234"))) {
      return new ResponseEntity<>("Invalid credentials. Please check the username and password.", HttpStatus.UNAUTHORIZED);
    } else {
      // Creating JWT using the user credentials.
      Map<String, Object> claims = new HashMap<>(8);
      claims.put("usr", login.getUsername());
      claims.put("sub", "Authentication token");
      claims.put("iss", Iconstants.ISSUER);
      claims.put("rol", "Administrator, Developer");
      claims.put("iat", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

      jwttoken = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, Iconstants.SECRET_KEY).compact();
      System.out.println("Returning the following token to the user= " + jwttoken);
    }

    return new ResponseEntity<>(jwttoken, HttpStatus.OK);
  }
}